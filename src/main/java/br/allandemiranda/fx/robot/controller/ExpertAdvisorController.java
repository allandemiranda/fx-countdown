package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.analysis.DMatrixPredictRowDto;
import br.allandemiranda.fx.robot.dto.analysis.PriceRiskLevelDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.dto.operation.OrderDto;
import br.allandemiranda.fx.robot.dto.operation.RequestSignalDto;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.impl.input.GarchInputService;
import br.allandemiranda.fx.robot.service.impl.input.PriceRiskLevelInputService;
import br.allandemiranda.fx.robot.service.impl.input.XGBoostInputService;
import br.allandemiranda.fx.robot.service.utils.CandlestickUtils;
import br.allandemiranda.fx.robot.service.utils.DMatrixUtils;
import br.allandemiranda.fx.robot.service.utils.GarchUtils;
import br.allandemiranda.fx.robot.service.utils.PriceRiskLevelUtils;
import br.allandemiranda.fx.robot.service.utils.XGBoostTrainerUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors")
public class ExpertAdvisorController {

  private final ExpertAdvisorService expertAdvisorService;
  private final SymbolService symbolService;
  private final XGBoostInputService xgBoostInputService;
  private final GarchInputService garchInputService;
  private final PriceRiskLevelInputService priceRiskLevelInputService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<ExpertAdvisorDto> findAll(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe) {
    log.debug("Find All [symbolName={}, timeframe={}]", symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMapMany(symbolDto -> this.getExpertAdvisorService().get(symbolDto, timeframe));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> find(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name) {
    log.debug("Find [name={}, symbolName={}, timeframe={}]", name, symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .doOnError(throwable -> log.warn("Trouble for finding ExpertAdvisor [name={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> update(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name,
      @RequestBody @Valid ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    log.debug("Update [name={}, symbolName={}, timeframe={}, expertAdvisorUpdateDto={}]", name, symbolName, timeframe, expertAdvisorUpdateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> this.getExpertAdvisorService().update(expertAdvisorDto, expertAdvisorUpdateDto))
        .doOnError(throwable -> log.warn("Trouble for update ExpertAdvisor [name={}, symbolName={}, timeframe={}]", name, symbolName, timeframe, throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}/order", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<OrderDto> getOrderAnalysis(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name,
      @RequestBody @Valid RequestSignalDto requestSignalDto) {
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMapMany(symbolDto -> {
      return ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()).flatMapMany(expertAdvisorDto -> {
        return this.getXgBoostInputService().get(expertAdvisorDto).flatMapMany(xgBoostInputDto -> {
          return this.getPriceRiskLevelInputService().get(expertAdvisorDto).flatMapMany(priceRiskLevelInputDto -> {
            return this.getGarchInputService().get(expertAdvisorDto).flatMapMany(garchInputDto -> {
              return Flux.fromArray(PositionType.values()).parallel()
                  .flatMap(positionType -> {
                    return (PositionType.POSITION_TYPE_BUY.equals(positionType) ?
                        GarchUtils.getGarchForecasts(garchInputDto, positionType).apply(requestSignalDto.candlesticks()) :
                        GarchUtils.getGarchForecasts(garchInputDto, positionType).apply(CandlestickUtils.getCandlesticksAsk(requestSignalDto.ticks(), timeframe))
                    ).flatMap(garchForecastDto -> {
                      try {
                        DMatrixPredictRowDto row = new DMatrixPredictRowDto(garchForecastDto, requestSignalDto.candlesticks(), requestSignalDto.adxs(), requestSignalDto.atrs(), requestSignalDto.bandss(), requestSignalDto.macds(), requestSignalDto.maFasts(), requestSignalDto.maSlows(), requestSignalDto.rsis(), requestSignalDto.stochastics());
                        float[] predicate = DMatrixUtils.getDMatrixPredict(row);
                        Path path = Paths.get(
                            System.getProperty("user.home") + File.separator + "ml_trading" + File.separator + expertAdvisorDto.name() + "_" + positionType.getTextValue() + "_" + expertAdvisorDto.symbolDto().name() + "_" + expertAdvisorDto.timeframe().getCode() + ".json");
                        Booster booster = XGBoostTrainerUtils.loadModel(path.toString());
                        float[][] result = XGBoostTrainerUtils.runPredicateSimple(predicate, booster);
                        BigDecimal minP = xgBoostInputDto.minimalLevelAccepted();

                        if (minP.compareTo(BigDecimal.valueOf(result[0][1])) <= 0) {
                          PriceRiskLevelDto priceRiskLevelDtoMono = PriceRiskLevelUtils.getPriceRiskLevelByGarchForecast(priceRiskLevelInputDto, symbolDto).apply(garchForecastDto);
                          OrderType orderType = positionType.equals(PositionType.POSITION_TYPE_BUY) ? OrderType.ORDER_TYPE_BUY : OrderType.ORDER_TYPE_SELL;
                          OrderDto orderDto = new OrderDto(symbolName, orderType, priceRiskLevelDtoMono.tpPrice(), priceRiskLevelDtoMono.slPrice(), "//todo create a message to show the % for not open and for open");
                          return Mono.just(orderDto);
                        }
                      } catch (RuntimeException | XGBoostError e) {
                        return Mono.error(e);
                      }

                      return Mono.empty();
                    });
                  });
            });
          });
        });
      });
    });
  }
}
