package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.analysis.DMatrixTrainRowDto;
import br.allandemiranda.fx.robot.dto.analysis.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.analysis.PriceRiskLevelDto;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.dto.InputCreateDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.enums.XGBoostLabel;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import br.allandemiranda.fx.robot.model.InputModel;
import br.allandemiranda.fx.robot.service.CandlestickService;
import br.allandemiranda.fx.robot.service.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.IndicatorService;
import br.allandemiranda.fx.robot.service.InputService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import br.allandemiranda.fx.robot.service.impl.indicator.ADXService;
import br.allandemiranda.fx.robot.service.impl.indicator.ATRService;
import br.allandemiranda.fx.robot.service.impl.indicator.BandsService;
import br.allandemiranda.fx.robot.service.impl.indicator.MACDService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaFastService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaSlowService;
import br.allandemiranda.fx.robot.service.impl.indicator.RSIService;
import br.allandemiranda.fx.robot.service.impl.indicator.StochasticService;
import br.allandemiranda.fx.robot.service.impl.input.GarchInputService;
import br.allandemiranda.fx.robot.service.impl.input.IADXService;
import br.allandemiranda.fx.robot.service.impl.input.IATRService;
import br.allandemiranda.fx.robot.service.impl.input.IBandsService;
import br.allandemiranda.fx.robot.service.impl.input.IMACDService;
import br.allandemiranda.fx.robot.service.impl.input.IMAFastService;
import br.allandemiranda.fx.robot.service.impl.input.IMASlowService;
import br.allandemiranda.fx.robot.service.impl.input.IRSIService;
import br.allandemiranda.fx.robot.service.impl.input.IStochasticService;
import br.allandemiranda.fx.robot.service.impl.input.PriceRiskLevelInputService;
import br.allandemiranda.fx.robot.service.impl.input.ScopeInputService;
import br.allandemiranda.fx.robot.service.impl.input.XGBoostInputService;
import br.allandemiranda.fx.robot.service.utils.CandlestickUtils;
import br.allandemiranda.fx.robot.service.utils.DMatrixUtils;
import br.allandemiranda.fx.robot.service.utils.GarchUtils;
import br.allandemiranda.fx.robot.service.utils.PriceRiskLevelUtils;
import br.allandemiranda.fx.robot.service.utils.ScopeUtils;
import br.allandemiranda.fx.robot.service.utils.TradingUtils;
import br.allandemiranda.fx.robot.service.utils.XGBoostTrainerUtils;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ml.dmlc.xgboost4j.java.XGBoostError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors")
public class ExpertAdvisorBuildingController {

  private final ExpertAdvisorService expertAdvisorService;
  private final SymbolService symbolService;
  private final CandlestickService candlestickService;
  private final TickService tickService;
  private final Executor executor;

  private final ADXService adxService;
  private final ATRService atrService;
  private final MACDService macdService;
  private final RSIService rsiService;
  private final BandsService bandsService;
  private final MaFastService maFastService;
  private final MaSlowService maSlowService;
  private final StochasticService stochasticService;

  private final GarchInputService garchInputService;
  private final IADXService iadxService;
  private final IATRService iatrService;
  private final IBandsService ibandsService;
  private final IMACDService imacdService;
  private final IMAFastService imaFastService;
  private final IMASlowService imaSlowService;
  private final IRSIService irsiService;
  private final IStochasticService iStochasticService;
  private final PriceRiskLevelInputService priceRiskLevelService;
  private final ScopeInputService scopeService;
  private final XGBoostInputService xgBoostInputService;

  private Collection<InputService<? extends InputModel, ? extends InputDto, ? extends InputCreateDto>> listOfInputServices;

  private Collection<IndicatorService<? extends IndicatorModel, ? extends IndicatorDto, ? extends IndicatorCreateDto>> listOfDataServices;

  @PostConstruct
  void init() {
    this.listOfInputServices = List.of(
        this.garchInputService,
        this.iadxService,
        this.iatrService,
        this.ibandsService,
        this.imacdService,
        this.imaFastService,
        this.imaSlowService,
        this.irsiService,
        this.iStochasticService,
        this.priceRiskLevelService,
        this.scopeService,
        this.xgBoostInputService
    );

    this.listOfDataServices = List.of(
        this.adxService,
        this.atrService,
        this.bandsService,
        this.macdService,
        this.maFastService,
        this.maSlowService,
        this.rsiService,
        this.stochasticService
    );
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> create(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @RequestBody @Valid ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    log.debug("Create [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto);

    Collection<Function<ExpertAdvisorDto, Mono<? extends InputDto>>> tasks = List.of(
        expertAdvisorDto -> this.getGarchInputService().create(expertAdvisorDto, expertAdvisorCreateDto.garch()),
        expertAdvisorDto -> this.getIadxService().create(expertAdvisorDto, expertAdvisorCreateDto.iadx()),
        expertAdvisorDto -> this.getIatrService().create(expertAdvisorDto, expertAdvisorCreateDto.iatr()),
        expertAdvisorDto -> this.getIbandsService().create(expertAdvisorDto, expertAdvisorCreateDto.ibands()),
        expertAdvisorDto -> this.getImacdService().create(expertAdvisorDto, expertAdvisorCreateDto.imacd()),
        expertAdvisorDto -> this.getImaFastService().create(expertAdvisorDto, expertAdvisorCreateDto.imaFast()),
        expertAdvisorDto -> this.getImaSlowService().create(expertAdvisorDto, expertAdvisorCreateDto.imaSlow()),
        expertAdvisorDto -> this.getIrsiService().create(expertAdvisorDto, expertAdvisorCreateDto.irsi()),
        expertAdvisorDto -> this.getIStochasticService().create(expertAdvisorDto, expertAdvisorCreateDto.iStochastic()),
        expertAdvisorDto -> this.getPriceRiskLevelService().create(expertAdvisorDto, expertAdvisorCreateDto.priceRiskLevel()),
        expertAdvisorDto -> this.getScopeService().create(expertAdvisorDto, expertAdvisorCreateDto.scope()),
        expertAdvisorDto -> this.getXgBoostInputService().create(expertAdvisorDto, expertAdvisorCreateDto.xgBoost())
    );

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> this.getExpertAdvisorService().create(symbolDto, timeframe, expertAdvisorCreateDto)).switchIfEmpty(Mono.defer(() -> {
          log.warn("Error creating expert advisor: create returned empty expert advisor [expertAdvisorCreateDto={}]", expertAdvisorCreateDto);
          return Mono.error(IllegalStateException::new);
        }))
        .flatMap(expertAdvisorDto -> Flux.fromIterable(tasks).parallel()
            .flatMap(task -> task.apply(expertAdvisorDto)
                .doOnError(throwable -> log.warn("Trouble for creating input for the expert advisor [expertAdvisorDto={}]", expertAdvisorDto, throwable))
                .switchIfEmpty(Mono.defer(() -> {
                  log.warn("Error creating a input for the expert advisor: create generated empty input [expertAdvisorDto={}]", expertAdvisorDto);
                  return Mono.error(IllegalStateException::new);
                }))
            )
            .sequential().then(Mono.just(expertAdvisorDto))
        );
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}/validate_scope", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> validateScope(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe,
      @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name) {
    log.info("Validate scope [symbolName={}, timeframe={}, name={}]", symbolName, timeframe, name);

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> this.getExpertAdvisorService().updateStatus(expertAdvisorDto, ExpertAdvisorStatus.VALIDATING_SCOPE)
            .then(Mono.defer(() -> {
                      Collection<Mono<ScopeInputCreateDto>> scopePublishers = this.getListOfDataServices().stream().map(indicatorService -> indicatorService.getScope(expertAdvisorDto)).collect(Collectors.toCollection(ArrayList::new));
                      Mono<ScopeInputCreateDto> tickScope = this.getTickService().getScope(expertAdvisorDto.symbolDto());
                      scopePublishers.add(tickScope);
                      Mono<ScopeInputCreateDto> candlestickScope = this.getCandlestickService().getScope(expertAdvisorDto.symbolDto(), timeframe);
                      scopePublishers.add(candlestickScope);
                      return ScopeUtils.calculateMergedScope(scopePublishers);
                    })
                    .flatMap(scopeInputCreateDto -> this.getScopeService().create(expertAdvisorDto, scopeInputCreateDto).flatMap(scopeInputDto -> {
                      log.info("Validate scope [symbolName={}, timeframe={}, name={}] updated to scopeInputCreateDto={}", symbolName, timeframe, name, scopeInputDto);
                      return this.getExpertAdvisorService().updateStatus(expertAdvisorDto, ExpertAdvisorStatus.VALIDATING_SCOPE_COMPLETE);
                    }))
                    .onErrorResume(throwable -> {
                      log.warn("Error validating scope [symbolName={}, timeframe={}, name={}]", symbolName, timeframe, name, throwable);
                      return this.getExpertAdvisorService().updateStatus(expertAdvisorDto, ExpertAdvisorStatus.VALIDATING_SCOPE_ERROR);
                    })
            ));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(path = "/generate_xgboost", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> generateXGBoost(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name) {
    log.info("Generate XGBoost [symbolName={}, timeframe={}, name={}]", symbolName, timeframe, name);

    SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMapMany(expertAdvisorDto -> {
          return this.getExpertAdvisorService().updateStatus(expertAdvisorDto, ExpertAdvisorStatus.BUILDING_START)
              .then(this.getXgBoostInputService().get(expertAdvisorDto))
                .flatMapMany(xgBoostInputDto -> {
                  return this.getGarchInputService().get(expertAdvisorDto)
                      .flatMapMany(garchInputDto -> {
                        return this.getPriceRiskLevelService().get(expertAdvisorDto)
                            .flatMapMany(priceRiskLevelInputDto -> {
                              return this.getScopeService().get(expertAdvisorDto)
                                  .flatMapMany(scopeInputDto -> {
                                    Mono<List<CandlestickDto>> candlesticksBuy = this.getCandlestickService().get(expertAdvisorDto.symbolDto(), expertAdvisorDto.timeframe(), scopeInputDto.startScope(), scopeInputDto.endScope()).collectList();
                                    Mono<List<CandlestickDto>> candlesticksSell = this.getTickService().getBetweenTimestamp(expertAdvisorDto.symbolDto(), scopeInputDto.startScope(), scopeInputDto.endScope().plus(expertAdvisorDto.timeframe().getDuration())).collectList().map(tickDtos -> CandlestickUtils.getCandlesticksAsk(tickDtos, expertAdvisorDto.timeframe()));
                                    return Mono.zip(candlesticksBuy, candlesticksSell)
                                        .flatMapMany(objects -> {
                                          List<CandlestickDto> candlesticksBid = objects.getT1();
                                          List<CandlestickDto> candlesticksAsk = objects.getT2();
                                          return ParallelFlux.from(GarchUtils.getGarchForecasts(garchInputDto, PositionType.POSITION_TYPE_BUY).apply(candlesticksBid),
                                                  GarchUtils.getGarchForecasts(garchInputDto, PositionType.POSITION_TYPE_SELL).apply(candlesticksAsk))
                                              .flatMap(garchForecastDto -> {
                                                      PriceRiskLevelDto priceRiskLevelDto = PriceRiskLevelUtils.getPriceRiskLevelByGarchForecast(priceRiskLevelInputDto, expertAdvisorDto.symbolDto()).apply(garchForecastDto);
                                                      return this.getTickService().getAfterTimestamp(expertAdvisorDto.symbolDto(), scopeInputDto.startScope()).collectList()
                                                          .flatMap(tickDtos -> TradingUtils.getTradingResult(priceRiskLevelDto, expertAdvisorDto.symbolDto()).apply(tickDtos))
                                                          .filter(tradingDto -> tradingDto.dealReason() != null)
                                                          .flatMap(tradingDto -> {
                                                      if(PositionType.POSITION_TYPE_BUY.equals(tradingDto.positionType())) {
                                                        return Mono.zip(Mono.just(tradingDto), Mono.just(candlesticksBid.getLast()),Mono.just(garchForecastDto));
                                                      } else {
                                                        return Mono.zip(Mono.just(tradingDto), Mono.just(candlesticksAsk.getLast()), Mono.just(garchForecastDto));
                                                      }
                                                    });
                                              });
                                        });
                                  });
                            });
                      }).map(objects -> objects.mapT1(tradingDto -> DealReason.DEAL_REASON_TP.equals(tradingDto.dealReason()) ? XGBoostLabel.OPEN : XGBoostLabel.NOT_OPEN))
                      .flatMap(technic -> {
                        XGBoostLabel xgBoostLabel = technic.getT1();
                        GarchForecastDto garchForecastDto = technic.getT3();
                        OffsetDateTime timestamp = technic.getT2().timestamp();
                        int horizon = xgBoostInputDto.horizon();
                        return Mono.zip(
                            Mono.zip(
                              Mono.just(xgBoostLabel),
                              Mono.just(garchForecastDto)
                            ),
                            Mono.zip(
                              this.getCandlestickService().getPreviousIndicators(expertAdvisorDto.symbolDto(), expertAdvisorDto.timeframe(), timestamp, horizon).collectList(),
                              this.getAdxService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getAtrService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getBandsService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList()
                            ),
                            Mono.zip(
                              this.getMacdService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getMaFastService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getMaSlowService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getRsiService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList(),
                              this.getStochasticService().getPreviousIndicators(expertAdvisorDto, timestamp, horizon).collectList()
                            )
                          );
                      })
                      .map(zip -> {
                        GarchForecastDto garchForecastDto = zip.getT1().getT2();
                        OffsetDateTime timestamp = garchForecastDto.timestamp();
                        XGBoostLabel xgBoostLabel = zip.getT1().getT1();
                        List<CandlestickDto> candlestickDtos = zip.getT2().getT1();
                        List<ADXDto> adxDtos = zip.getT2().getT2();
                        List<ATRDto> atrDtos = zip.getT2().getT3();
                        List<BandsDto> bandsDtos = zip.getT2().getT4();
                        List<MACDDto> macdDtos = zip.getT3().getT1();
                        List<MaFastDto> maFastDtos = zip.getT3().getT2();
                        List<MaSlowDto> maSlowDtos = zip.getT3().getT3();
                        List<RSIDto> rsiDtos = zip.getT3().getT4();
                        List<StochasticDto> stochasticDtos = zip.getT3().getT5();
                        return new DMatrixTrainRowDto(timestamp, xgBoostLabel, garchForecastDto, candlestickDtos, adxDtos, atrDtos, bandsDtos, macdDtos, maFastDtos, maSlowDtos, rsiDtos, stochasticDtos);
                      })
                      .groupBy(dMatrixTrainRowDto -> dMatrixTrainRowDto.garchForecastDto().positionType())
                      .parallel()
                      .flatMap(group -> {
                        return group.sort(Comparator.comparing(dMatrixTrainRowDto -> dMatrixTrainRowDto.garchForecastDto().timestamp())).map(dMatrixTrainRowDtos -> DMatrixUtils.toLibSvmRow(dMatrixTrainRowDtos)).collectList().flatMap(lines ->  {
                          Path path = Paths.get(System.getProperty("user.home") + File.separator + "ml_trading" + File.separator + expertAdvisorDto.name() + "_" + group.key().getTextValue() + "_" + expertAdvisorDto.symbolDto().name() + "_" + expertAdvisorDto.timeframe().getCode() + ".libsvm");
                          try {
                            return Mono.just(DMatrixUtils.saveToLibSvmFile(lines, path));
                          } catch (IOException e) {
                            return Mono.error(e);
                          }
                        });
                      })
                      .map(path -> {
                        Map<String, Object> params = XGBoostTrainerUtils.getDefaultParams(2, xgBoostInputDto.maxDepth(), xgBoostInputDto.eta(),xgBoostInputDto.subsample(),xgBoostInputDto.colSampleByTree(), xgBoostInputDto.minChildWeight(), xgBoostInputDto.lambda(), xgBoostInputDto.alpha(), true, 0, null);
                        return Map.entry(path, params);
                      })
                      .flatMap(pathMapEntry -> {
                        Path path = Paths.get(pathMapEntry.getKey().toString().replace(".libsvm", ".json"));
                        try {
                          return Mono.just(XGBoostTrainerUtils.trainAll(pathMapEntry.getValue(), DMatrixUtils.getDMatrix(pathMapEntry.getKey()), 100, path));
                        } catch (XGBoostError e) {
                          return Mono.error(e);
                        }
                      })
                      .collectSortedList((o1, o2) -> 0)
                      .flatMap(boosters -> boosters.size() != 2 ? Mono.error(IllegalStateException::new) : Mono.just(boosters))
                      .then(this.getExpertAdvisorService().updateStatus(expertAdvisorDto, ExpertAdvisorStatus.REDY_TO_USE));
                });
        })
        .subscribeOn(Schedulers.fromExecutor(this.getExecutor()))
        .subscribe(
            expertAdvisorDto -> log.info("Generated ML [name={}, ] done: expertAdvisorDto={}", name, expertAdvisorDto),
            throwable -> log.error("Error to generated ML [name={}, ]", name, throwable)
        );

    return Mono.empty();
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(path = "/{name}/clean_build", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> cleanInputAndIndicatorData(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe,
      @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name) {
    log.debug("Clean Input and Indicator data [name={}]", name);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMapMany(expertAdvisorDto -> {
          Stream<Mono<Void>> indicatorServices = this.getListOfDataServices().stream().map(indicatorService -> indicatorService.delete(expertAdvisorDto));
          return Flux.fromStream(indicatorServices);
        })
        .parallel().then()
        .doOnError(throwable -> log.warn("Trouble for Clean Input and Indicator data expert adviser [name={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> delete(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name) {
    log.debug("Delete [name={}]", name);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> {
          Stream<Mono<Void>> indicatorServices = this.getListOfDataServices().stream().map(indicatorService -> indicatorService.delete(expertAdvisorDto));
          Stream<Mono<Void>> inputServices = this.getListOfInputServices().stream().map(inputService -> inputService.delete(expertAdvisorDto));
          return Flux.fromStream(Stream.concat(indicatorServices, inputServices)).then(this.getExpertAdvisorService().delete(expertAdvisorDto));
        })
        .doOnError(throwable -> log.warn("Trouble for deleting expert adviser [name={}]", name, throwable));
  }
}
