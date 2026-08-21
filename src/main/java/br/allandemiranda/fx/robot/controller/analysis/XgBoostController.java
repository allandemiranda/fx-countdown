package br.allandemiranda.fx.robot.controller.analysis;

import br.allandemiranda.fx.robot.annotation.field.EaName;
import br.allandemiranda.fx.robot.annotation.field.SymbolName;
import br.allandemiranda.fx.robot.annotation.field.Version;
import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.analysis.XgBoostDto;
import br.allandemiranda.fx.robot.dto.analysis.create.XgBoostCreateDto;
import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.GarchInputDto;
import br.allandemiranda.fx.robot.dto.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.dto.input.ScopeInputDto;
import br.allandemiranda.fx.robot.dto.input.XgBoostInputDto;
import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import br.allandemiranda.fx.robot.dto.provider.DMatrixRow;
import br.allandemiranda.fx.robot.dto.provider.Garch;
import br.allandemiranda.fx.robot.dto.provider.GarchForecast;
import br.allandemiranda.fx.robot.dto.provider.Order;
import br.allandemiranda.fx.robot.dto.provider.PriceRiskLevel;
import br.allandemiranda.fx.robot.dto.provider.RequestSignal;
import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.enums.XgBoostLabel;
import br.allandemiranda.fx.robot.enums.XgBoostWatch;
import br.allandemiranda.fx.robot.exception.impl.ExpertAdvisorConflictException;
import br.allandemiranda.fx.robot.model.indicator.ADX;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import br.allandemiranda.fx.robot.model.indicator.MACD;
import br.allandemiranda.fx.robot.model.indicator.MaFast;
import br.allandemiranda.fx.robot.model.indicator.MaSlow;
import br.allandemiranda.fx.robot.model.indicator.RSI;
import br.allandemiranda.fx.robot.model.indicator.Stochastic;
import br.allandemiranda.fx.robot.service.analysis.BoosterService;
import br.allandemiranda.fx.robot.service.analysis.XgBoostService;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.core.TickService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.impl.ADXService;
import br.allandemiranda.fx.robot.service.indicator.impl.ATRService;
import br.allandemiranda.fx.robot.service.indicator.impl.BandsService;
import br.allandemiranda.fx.robot.service.indicator.impl.MACDService;
import br.allandemiranda.fx.robot.service.indicator.impl.MaFastService;
import br.allandemiranda.fx.robot.service.indicator.impl.MaSlowService;
import br.allandemiranda.fx.robot.service.indicator.impl.RSIService;
import br.allandemiranda.fx.robot.service.indicator.impl.StochasticService;
import br.allandemiranda.fx.robot.service.input.impl.GarchInputService;
import br.allandemiranda.fx.robot.service.input.impl.PriceRiskLevelInputService;
import br.allandemiranda.fx.robot.service.input.impl.ScopeInputService;
import br.allandemiranda.fx.robot.service.input.impl.XgBoostInputService;
import br.allandemiranda.fx.robot.service.utils.CandlestickUtils;
import br.allandemiranda.fx.robot.service.utils.DMatrixUtils;
import br.allandemiranda.fx.robot.service.utils.GarchUtils;
import br.allandemiranda.fx.robot.service.utils.PriceRiskLevelUtils;
import br.allandemiranda.fx.robot.service.utils.XgBoostUtils;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/candlesticks/{timeframe}/ea/{eaName}/xgboosts")
public class XgBoostController {

  private final ADXService adxService;
  private final ATRService atrService;
  private final BandsService bandsService;
  private final BoosterService boosterService;
  private final Executor executor;
  private final ExpertAdvisorService expertAdvisorService;
  private final GarchInputService garchInputService;
  private final MaFastService maFastService;
  private final MaSlowService maSlowService;
  private final MACDService macdService;
  private final PriceRiskLevelInputService priceRiskLevelInputService;
  private final RSIService rsiService;
  private final ScopeInputService scopeInputService;
  private final StochasticService stochasticService;
  private final SymbolService symbolService;
  private final TickService tickService;
  private final XgBoostInputService xgBoostInputService;
  private final XgBoostService xgBoostService;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> create(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String eaName, @RequestBody @Version @Valid String version) {
    log.info("Generate XGBoost requested for [symbolName={}, timeframe={}, eaName={}, version={}]", symbolName, timeframe, eaName, version);

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> {
          if (!EAStatus.VALIDATING_SCOPE_COMPLETE.equals(expertAdvisorDto.status())) {
            return Mono.error(new ExpertAdvisorConflictException(String.format("Cannot generate XGBoost: Expert Advisor is in status %s, expected VALIDATING_SCOPE_COMPLETE or READY_TO_USE", expertAdvisorDto.status())));
          } else {
            return this.getExpertAdvisorService().updateStatus(expertAdvisorDto, EAStatus.BUILDING_START).zipWith(this.getScopeInputService().get(expertAdvisorDto));
          }
        })
        .doOnSuccess(objects -> {
          ExpertAdvisorDto expertAdvisorDto = objects.getT1();
          ScopeInputDto scopeInputDto = objects.getT2();

          this.getExecutor().execute(() -> {
            log.info("batch job started");
            Mono.zip(
                    Mono.zip(
                        Mono.just(expertAdvisorDto),
                        this.getGarchInputService().get(expertAdvisorDto),
                        this.getPriceRiskLevelInputService().get(expertAdvisorDto),
                        this.getXgBoostInputService().get(expertAdvisorDto),
                        this.getTickService().getBetweenTimestamp(symbolName, scopeInputDto.startScope(), scopeInputDto.endScope()).collectList(),
                        SymbolUtils.getSymbol(symbolName, this.getSymbolService()),
                        this.getXgBoostInputService().get(expertAdvisorDto)
                    ),
                    Mono.zip(
                        this.getAdxService().get(expertAdvisorDto).collectList(),
                        this.getAtrService().get(expertAdvisorDto).collectList(),
                        this.getBandsService().get(expertAdvisorDto).collectList(),
                        this.getMaFastService().get(expertAdvisorDto).collectList(),
                        this.getMaSlowService().get(expertAdvisorDto).collectList(),
                        this.getMacdService().get(expertAdvisorDto).collectList(),
                        this.getRsiService().get(expertAdvisorDto).collectList(),
                        this.getStochasticService().get(expertAdvisorDto).collectList()
                    )
                ).flatMap(dataObjects -> {
                  // Generates the dataset files
                  Map<Object, Map<Object, Object[]>> dataSetFiles = DMatrixUtils.generateDataSetFile(
                      dataObjects.getT1().getT2(),
                      dataObjects.getT1().getT3(),
                      dataObjects.getT1().getT4(),
                      timeframe,
                      dataObjects.getT1().getT5(),
                      dataObjects.getT1().getT6(),
                      dataObjects.getT2().getT1(),
                      dataObjects.getT2().getT2(),
                      dataObjects.getT2().getT3(),
                      dataObjects.getT2().getT4(),
                      dataObjects.getT2().getT5(),
                      dataObjects.getT2().getT6(),
                      dataObjects.getT2().getT7(),
                      dataObjects.getT2().getT8()
                  );

                  System.gc();

                  XgBoostInputDto xgBoostInputDto = dataObjects.getT1().getT7();

                  return Flux.fromIterable(dataSetFiles.entrySet())
                      .map(entry -> {
                        OrderType orderType = (OrderType) entry.getKey();
                        Map<XgBoostWatch, Object[]> xgBoostWatchMap = (Map<XgBoostWatch, Object[]>) (Object) entry.getValue();

                        DMatrix dMatrixTrain = DMatrixUtils.readDataSet((Path) xgBoostWatchMap.get(XgBoostWatch.TRAIN)[0]);
                        DMatrix dMatrixValidation = DMatrixUtils.readDataSet((Path) xgBoostWatchMap.get(XgBoostWatch.VALIDATION)[0]);
                        Booster booster = XgBoostUtils.trainAndValidation(dMatrixTrain, dMatrixValidation, xgBoostInputDto, true, 0);

                        byte[] boostered = XgBoostUtils.boosterToBytes(booster);
                        int sizeDataSet = (int) xgBoostWatchMap.get(XgBoostWatch.TRAIN)[1];
                        OffsetDateTime lastTimestamp = (OffsetDateTime) xgBoostWatchMap.get(XgBoostWatch.TRAIN)[2];

                        return new Object[]{orderType, boostered, sizeDataSet, lastTimestamp};
                      })
                      .collectMap(o -> o[0], o -> new Object[]{o[1], o[2], o[3]})
                      .flatMap(objectMap -> {
                        XgBoostCreateDto createDto = new XgBoostCreateDto(
                            version,
                            (byte[]) objectMap.get(OrderType.ORDER_TYPE_BUY)[0],
                            (byte[]) objectMap.get(OrderType.ORDER_TYPE_SELL)[0],
                            (int) objectMap.get(OrderType.ORDER_TYPE_BUY)[1],
                            (int) objectMap.get(OrderType.ORDER_TYPE_SELL)[1],
                            (OffsetDateTime) objectMap.get(OrderType.ORDER_TYPE_BUY)[2],
                            (OffsetDateTime) objectMap.get(OrderType.ORDER_TYPE_SELL)[2]
                        );
                        return this.getXgBoostService().create(symbolName, timeframe, eaName, createDto)
                            .then(this.getExpertAdvisorService().updateStatus(expertAdvisorDto, EAStatus.READY_TO_USE));
                      });
                })
                .onErrorResume(throwable -> {
                  log.error("Pipeline failed for EA [eaName={}]: {}", eaName, throwable.getMessage(), throwable);
                  return this.getExpertAdvisorService().updateStatus(expertAdvisorDto, EAStatus.BUILDING_ERROR);
                })
                .subscribe();
          });
        })
        .then();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{version}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<XgBoostDto> find(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String eaName, @PathVariable @Version @Valid String version) {
    log.debug("Find XgBoost");
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> this.getXgBoostService().get(expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), expertAdvisorDto.eaName(), version));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<XgBoostDto> findAll(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String eaName) {
    log.debug("Find All XgBoost");
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, this.getExpertAdvisorService()))
        .flatMapMany(expertAdvisorDto -> this.getXgBoostService().get(expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), expertAdvisorDto.eaName()));
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/{version}/buy", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Order> getOrderAnalysisBuy(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName String eaName, @PathVariable @Version @Valid String version,
      @RequestBody @Valid RequestSignal requestSignal) {
    log.debug("Get buy order analysis for EA [eaName={}, symbolName={}, timeframe={}]", eaName, symbolName, timeframe);

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, this.getExpertAdvisorService())
            .filter(expertAdvisorDto -> EAStatus.READY_TO_USE.equals(expertAdvisorDto.status()))
            .flatMap(expertAdvisorDto -> Mono.zip(this.getGarchInputService().get(expertAdvisorDto), this.getXgBoostInputService().get(expertAdvisorDto), this.getBoosterService().getBuyBooster(eaName, version),
                this.getPriceRiskLevelInputService().get(expertAdvisorDto)))
            .flatMap(objects -> {
              GarchInputDto garchInputDto = objects.getT1();
              XgBoostInputDto xgBoostInputDto = objects.getT2();
              Booster booster = objects.getT3();
              PriceRiskLevelInputDto priceRiskLevelInputDto = objects.getT4();

              List<Candlestick> candlesticks = CandlestickUtils.getCandlesticksByTicks(requestSignal.ticks(), timeframe, OrderType.ORDER_TYPE_BUY);
              List<GarchForecast> garchForecasts = GarchUtils.getGarchForecasts(garchInputDto, OrderType.ORDER_TYPE_BUY).apply(candlesticks).toList();
              List<? extends Candlestick> candlesticksScope = candlesticks.stream().skip(candlesticks.size() - xgBoostInputDto.horizon()).toList();

              DMatrixRow dMatrixRow = new DMatrixRow() {
                @Override
                public List<? extends ADX> adxs() {
                  return requestSignal.adxs();
                }

                @Override
                public List<? extends ATR> atrs() {
                  return requestSignal.atrs();
                }

                @Override
                public List<? extends Bands> bandss() {
                  return requestSignal.bandss();
                }

                @Override
                public List<? extends Candlestick> candlesticks() {
                  return candlesticksScope;
                }

                @Override
                public Garch garch() {
                  return garchForecasts.getFirst();
                }

                @Override
                public List<? extends MaFast> maFasts() {
                  return requestSignal.maFasts();
                }

                @Override
                public List<? extends MaSlow> maSlows() {
                  return requestSignal.maSlows();
                }

                @Override
                public List<? extends MACD> macds() {
                  return requestSignal.macds();
                }

                @Override
                public List<? extends RSI> rsis() {
                  return requestSignal.rsis();
                }

                @Override
                public List<? extends Stochastic> stochastics() {
                  return requestSignal.stochastics();
                }
              };

              float[] line = DMatrixUtils.extractAllFeatures(dMatrixRow)
                  .stream()
                  .mapToDouble(Float::doubleValue)
                  .collect(
                      () -> new float[0],
                      (array, value) -> {
                      },
                      (a, b) -> {
                      }
                  );

              float[][] predicateSimple = XgBoostUtils.runPredicateSimple(line, booster);
              float probability = predicateSimple[0][0];
              BigDecimal probabilityBd = BigDecimal.valueOf(probability);
              XgBoostLabel label = probabilityBd.compareTo(xgBoostInputDto.minimalLevelAccepted()) >= 0 ? XgBoostLabel.OPEN : XgBoostLabel.NOT_OPEN;

              if (XgBoostLabel.OPEN.equals(label)) {
                PriceRiskLevel riskLevel = PriceRiskLevelUtils.getPriceRiskLevelByGarchForecast(priceRiskLevelInputDto, symbolDto).apply(garchForecasts.getFirst());

                Order order = new Order() {
                  @Override
                  public String comment() {
                    return "Time> " + riskLevel.timestamp() + " > Probability " + probability + " to open a BUY operation";
                  }

                  @Override
                  public OrderType orderType() {
                    return OrderType.ORDER_TYPE_BUY;
                  }

                  @Override
                  public BigDecimal slPrice() {
                    return riskLevel.slPrice();
                  }

                  @Override
                  public OffsetDateTime timestamp() {
                    return riskLevel.timestamp();
                  }

                  @Override
                  public BigDecimal tpPrice() {
                    return riskLevel.tpPrice();
                  }
                };

                return Mono.just(order);
              } else {
                return Mono.empty();
              }
            }));
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/{version}/sell", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Order> getOrderAnalysisSell(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName String eaName, @PathVariable @Version @Valid String version,
      @RequestBody @Valid RequestSignal requestSignal) {
    log.debug("Get sell order analysis for EA [eaName={}, symbolName={}, timeframe={}]", eaName, symbolName, timeframe);

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, eaName, this.getExpertAdvisorService())
            .filter(expertAdvisorDto -> EAStatus.READY_TO_USE.equals(expertAdvisorDto.status()))
            .flatMap(expertAdvisorDto -> Mono.zip(this.getGarchInputService().get(expertAdvisorDto), this.getXgBoostInputService().get(expertAdvisorDto), this.getBoosterService().getSellBooster(eaName, version),
                this.getPriceRiskLevelInputService().get(expertAdvisorDto)))
            .flatMap(objects -> {
              GarchInputDto garchInputDto = objects.getT1();
              XgBoostInputDto xgBoostInputDto = objects.getT2();
              Booster booster = objects.getT3();
              PriceRiskLevelInputDto priceRiskLevelInputDto = objects.getT4();

              List<Candlestick> candlesticks = CandlestickUtils.getCandlesticksByTicks(requestSignal.ticks(), timeframe, OrderType.ORDER_TYPE_SELL);
              List<GarchForecast> garchForecasts = GarchUtils.getGarchForecasts(garchInputDto, OrderType.ORDER_TYPE_SELL).apply(candlesticks).toList();
              List<? extends Candlestick> candlesticksScope = candlesticks.stream().skip(candlesticks.size() - xgBoostInputDto.horizon()).toList();

              DMatrixRow dMatrixRow = new DMatrixRow() {
                @Override
                public List<? extends ADX> adxs() {
                  return requestSignal.adxs();
                }

                @Override
                public List<? extends ATR> atrs() {
                  return requestSignal.atrs();
                }

                @Override
                public List<? extends Bands> bandss() {
                  return requestSignal.bandss();
                }

                @Override
                public List<? extends Candlestick> candlesticks() {
                  return candlesticksScope;
                }

                @Override
                public Garch garch() {
                  return garchForecasts.getFirst();
                }

                @Override
                public List<? extends MaFast> maFasts() {
                  return requestSignal.maFasts();
                }

                @Override
                public List<? extends MaSlow> maSlows() {
                  return requestSignal.maSlows();
                }

                @Override
                public List<? extends MACD> macds() {
                  return requestSignal.macds();
                }

                @Override
                public List<? extends RSI> rsis() {
                  return requestSignal.rsis();
                }

                @Override
                public List<? extends Stochastic> stochastics() {
                  return requestSignal.stochastics();
                }
              };

              float[] line = DMatrixUtils.extractAllFeatures(dMatrixRow)
                  .stream()
                  .mapToDouble(Float::doubleValue)
                  .collect(
                      () -> new float[0],
                      (array, value) -> {
                      },
                      (a, b) -> {
                      }
                  );

              float[][] predicateSimple = XgBoostUtils.runPredicateSimple(line, booster);
              float probability = predicateSimple[0][0];
              BigDecimal probabilityBd = BigDecimal.valueOf(probability);
              XgBoostLabel label = probabilityBd.compareTo(xgBoostInputDto.minimalLevelAccepted()) >= 0 ? XgBoostLabel.OPEN : XgBoostLabel.NOT_OPEN;

              if (XgBoostLabel.OPEN.equals(label)) {
                PriceRiskLevel riskLevel = PriceRiskLevelUtils.getPriceRiskLevelByGarchForecast(priceRiskLevelInputDto, symbolDto).apply(garchForecasts.getFirst());

                Order order = new Order() {
                  @Override
                  public String comment() {
                    return "Time> " + riskLevel.timestamp() + " > Probability " + probability + " to open a SELL operation";
                  }

                  @Override
                  public OrderType orderType() {
                    return OrderType.ORDER_TYPE_SELL;
                  }

                  @Override
                  public BigDecimal slPrice() {
                    return riskLevel.slPrice();
                  }

                  @Override
                  public OffsetDateTime timestamp() {
                    return riskLevel.timestamp();
                  }

                  @Override
                  public BigDecimal tpPrice() {
                    return riskLevel.tpPrice();
                  }
                };

                return Mono.just(order);
              } else {
                return Mono.empty();
              }
            }));
  }
}
