package br.allandemiranda.fx.robot.controller.impl;

import br.allandemiranda.fx.robot.controller.InputObjectController;
import br.allandemiranda.fx.robot.dto.TimeLineObjectDto;
import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.base.TickDto;
import br.allandemiranda.fx.robot.dto.impl.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.MLLabel;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.impl.Dashboard;
import br.allandemiranda.fx.robot.service.ChartService;
import br.allandemiranda.fx.robot.service.SymbolService;
import br.allandemiranda.fx.robot.service.TickService;
import br.allandemiranda.fx.robot.service.impl.ADXService;
import br.allandemiranda.fx.robot.service.impl.ATRService;
import br.allandemiranda.fx.robot.service.impl.BandsService;
import br.allandemiranda.fx.robot.service.impl.CandlestickService;
import br.allandemiranda.fx.robot.service.impl.DashboardService;
import br.allandemiranda.fx.robot.service.impl.GarchForecastService;
import br.allandemiranda.fx.robot.service.impl.GarchInputService;
import br.allandemiranda.fx.robot.service.impl.GarchTradingService;
import br.allandemiranda.fx.robot.service.impl.MACDService;
import br.allandemiranda.fx.robot.service.impl.MaFastService;
import br.allandemiranda.fx.robot.service.impl.MaSlowService;
import br.allandemiranda.fx.robot.service.impl.RSIService;
import br.allandemiranda.fx.robot.service.impl.StochasticService;
import br.allandemiranda.fx.robot.util.DashboardUtils;
import br.allandemiranda.fx.robot.util.RiskLevelCalculatorUtils;
import br.allandemiranda.fx.robot.util.TradingUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{name}/timeframes/{period}/dashboards")
public class DashboardController implements InputObjectController<Dashboard, DashboardDto, DashboardCreateDto> {

  private final Executor executor;
  private final SymbolService symbolService;
  private final ChartService chartService;
  private final DashboardService service;
  private final ADXService adxService;
  private final ATRService atrService;
  private final BandsService bandsService;
  private final CandlestickService candlestickService;
  private final MACDService macdService;
  private final MaFastService maFastService;
  private final MaSlowService maSlowService;
  private final RSIService rsiService;
  private final StochasticService stochasticService;
  private final TickService tickService;
  private final GarchInputService garchInputService;
  private final GarchForecastService garchForecastService;
  private final GarchTradingService garchTradingService;

  @Override
  public String getInputObjectName() {
    return "dashboard";
  }

  @Override
  public Mono<DashboardDto> create(String name, Timeframe period, DashboardCreateDto createInputObjectDto) {
    log.info("Create [name={}, period={}, createInputObjectDto={}]", name, period, createInputObjectDto);
    return InputObjectController.super.create(name, period, createInputObjectDto);
  }

  private <T extends TimeLineObjectDto> Function<DashboardDto, Mono<DashboardDto>> getRealScopeTimeAndUpdate(Flux<T> timeLineObjects) {
    log.trace("getRealScopeTimeAndUpdate()");
    return dashboardDto -> DashboardUtils.getRealScopeTime(dashboardDto, timeLineObjects).flatMap(objects -> this.getService().updateScope(dashboardDto.chartDto(), objects.getT1(), objects.getT2()));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/validate_scope", produces = "application/json")
  public Mono<DashboardDto> validateScope(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    log.info("Validate scope [name={}, period={}]", name, period);
    return this.getChartDto(name, period).flatMap(chartDto -> this.getService().updateStatus(chartDto, DashboardStatus.VALIDATING_SCOPE)
        .flatMap(this.getRealScopeTimeAndUpdate(this.getAdxService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getAtrService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getBandsService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getCandlestickService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getMacdService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getMaFastService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getMaSlowService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getRsiService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getStochasticService().get(chartDto)))
        .flatMap(this.getRealScopeTimeAndUpdate(this.getTickService().get(chartDto.symbol())))
        .then(this.getService().updateStatus(chartDto, DashboardStatus.VALIDATING_SCOPE_COMPLETE))
        .onErrorResume(throwable -> {
          log.error("Error validating scope [name={}, period={}]: {}", name, period, throwable.getMessage());
          return this.getService().updateStatus(chartDto, DashboardStatus.VALIDATING_SCOPE_ERROR).then(Mono.error(throwable));
        }));
  }

  private Function<DashboardDto, Mono<DashboardDto>> getGarchForecasts() {
    return dashboardDto -> {
      ChartDto chartDto = dashboardDto.chartDto();
      log.debug("getGarchForecasts(): chartDto={}", chartDto);

      return this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_FORECASTS)
          .then(this.getGarchInputService().get(chartDto))
          .flatMapMany(garchInputDto -> {
            log.debug("getGarchForecasts(): garchInputDto={}, computing getGarchForecastCreateDtoFlux()", garchInputDto);
            return DashboardUtils.getGarchForecastCreateDtoFlux(this.getCandlestickService().get(garchInputDto.chartDto()), dashboardDto, garchInputDto);
          })
          .concatMap(garchForecastCreateDto -> {
            log.debug("getGarchForecasts(): garchForecastCreateDto={}, creating Garch Forecast [chartDto={}]", garchForecastCreateDto, chartDto);
            return this.getGarchForecastService().create(chartDto, garchForecastCreateDto);
          })
          .collectList()
          .flatMap(garchForecastDtos -> garchForecastDtos.isEmpty() ? Mono.error(IllegalStateException::new) : this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_FORECASTS_COMPLETE))
          .onErrorResume(throwable -> {
            log.error("Error generate Garch Forecast [name={}, period={}]: {}", chartDto.symbol().name(), chartDto.period(), throwable.getMessage());
            return this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_FORECASTS_ERROR).then(Mono.error(throwable));
          });
    };
  }

  private Function<DashboardDto, Mono<DashboardDto>> getGarchTradings() {
    return dashboardDto -> {
      ChartDto chartDto = dashboardDto.chartDto();
      log.debug("getGarchTradings(): chartDto={}", chartDto);

      return this.getService().updateStatus(chartDto, DashboardStatus.BUILDING_GARCH_TRADINGS)
          .then(this.getGarchInputService().get(chartDto)).flatMap(garchInputDto -> {
            log.debug("getGarchTradings(): garchInputDto={}", garchInputDto);

            BigDecimal kSL = garchInputDto.kSL();
            BigDecimal kTP = garchInputDto.kTP();

            return this.getGarchForecastService().get(chartDto).parallel().runOn(Schedulers.parallel())
                .flatMap(garchForecastDto -> {
                  log.debug("getGarchTradings(): garchForecastDto={}", garchForecastDto);

                  OffsetDateTime timestamp = garchForecastDto.timestamp();
                  SymbolDto symbol = chartDto.symbol();
                  BigDecimal pipSize = symbol.point();
                  BigDecimal sigmaAgg = garchForecastDto.sigmaAgg();

                  return this.getTickService().getOrNext(symbol, timestamp)
                      .flatMap(tickDto -> {
                        log.debug("getGarchTradings(): tickDto={}, garchForecastDto={}", tickDto, garchForecastDto);

                        BigDecimal priceOpenBuy = tickDto.ask();
                        BigDecimal priceOpenSell = tickDto.bid();

                        return Mono.zip(Mono.just(RiskLevelCalculatorUtils.fromSigmaAgg(priceOpenBuy, pipSize, PositionType.POSITION_TYPE_BUY, sigmaAgg, kSL, kTP)), Mono.just(RiskLevelCalculatorUtils.fromSigmaAgg(priceOpenSell, pipSize, PositionType.POSITION_TYPE_SELL, sigmaAgg, kSL, kTP)))
                            .flatMap(objects -> {
                              RiskLevelCalculatorUtils.LevelPrice levelPriceBuy = objects.getT1();
                              RiskLevelCalculatorUtils.LevelPrice levelPriceSell = objects.getT2();
                              GarchTradingCreateDto garchTradingCreateDtoBuy = new GarchTradingCreateDto(timestamp, tickDto, levelPriceBuy.tpPrice(), levelPriceBuy.slPrice(), levelPriceSell.tpPrice(), levelPriceSell.slPrice());

                              return Mono.just(garchTradingCreateDtoBuy);
                            });
                      });
                })
                .sequential().concatMap(garchTradingCreateDto -> {
                  log.debug("getGarchTradings(): garchTradingCreateDto={}, creating Garch Trading [chartDto={}]", garchTradingCreateDto, chartDto);
                  return this.getGarchTradingService().create(chartDto, garchTradingCreateDto);
                })
                .collectList()
                .flatMap(garchTradingDtos -> garchTradingDtos.isEmpty() ? Mono.error(IllegalStateException::new) : this.getService().updateStatus(chartDto, DashboardStatus.BUILDING_GARCH_TRADINGS_COMPLETE))
                .onErrorResume(throwable -> {
                  log.error("Error generate Garch Tradings [name={}, period={}]: {}", chartDto.symbol().name(), chartDto.period(), throwable.getMessage());
                  return this.getService().updateStatus(chartDto, DashboardStatus.BUILDING_GARCH_TRADINGS_ERROR).then(Mono.error(throwable));
                });
          });
    };
  }

  private Function<DashboardDto, Mono<DashboardDto>> getGarchTradingScenarios() {
    record Trading(BigDecimal points, BigDecimal closePrice, DealReason dealReason) {

    }

    return dashboardDto -> {
      ChartDto chartDto = dashboardDto.chartDto();
      SymbolDto symbol = chartDto.symbol();

      log.debug("getGarchTradingScenarios(): chartDto={}", chartDto);

      return this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_SCENARIO)
          .thenMany(this.getTickService().get(symbol).filter(tickDto -> tickDto.timestamp().isEqual(dashboardDto.startScope()) || tickDto.timestamp().isAfter(dashboardDto.startScope())))
          .flatMap(tickDto -> {
            log.debug("getGarchTradingScenarios(): tickDto={}", tickDto);
            return this.getGarchTradingService().get(chartDto).parallel().runOn(Schedulers.parallel())
                .filter(garchTradingDto -> (garchTradingDto.buyDealReason() == null && garchTradingDto.closeBuyTime().isAfter(tickDto.timestamp())) || (garchTradingDto.sellDealReason() == null && garchTradingDto.closeSellTime().isAfter(tickDto.timestamp())))
                .flatMap(garchTradingDto -> {
                  log.debug("getGarchTradingScenarios(): garchTradingDto={}", garchTradingDto);
                  Optional<Trading> buyTrading = Optional.empty();
                  Optional<Trading> sellTrading = Optional.empty();

                  if (garchTradingDto.buyDealReason() != null) {
                    DealReason newDealReason = TradingUtils.getDealReason(tickDto.ask(), tickDto.bid(), garchTradingDto.buyTpPrice(), garchTradingDto.buySlPrice(), PositionType.POSITION_TYPE_BUY);
                    BigDecimal rollover = TradingUtils.getRollover(garchTradingDto.openTime(), tickDto.timestamp(), symbol.swapLong(), symbol.swapShort(), PositionType.POSITION_TYPE_BUY);
                    BigDecimal points = TradingUtils.getPoints(garchTradingDto.buyOpenPrice(), tickDto.bid(), symbol.point(), PositionType.POSITION_TYPE_BUY).add(rollover);
                    if (newDealReason != null) {
                      buyTrading = Optional.of(new Trading(points, tickDto.bid(), newDealReason));
                    } else {
                      BigDecimal tpInPoints = TradingUtils.getPoints(garchTradingDto.buyOpenPrice(), garchTradingDto.buyTpPrice(), symbol.point(), PositionType.POSITION_TYPE_BUY);
                      if (tpInPoints.add(rollover).compareTo(BigDecimal.ZERO) <= 0) {
                        buyTrading = Optional.of(new Trading(points, tickDto.bid(), DealReason.DEAL_REASON_ROLLOVER));
                      }
                    }
                  }

                  if (garchTradingDto.sellDealReason() != null) {
                    DealReason newDealReason = TradingUtils.getDealReason(tickDto.ask(), tickDto.bid(), garchTradingDto.buyTpPrice(), garchTradingDto.buySlPrice(), PositionType.POSITION_TYPE_SELL);
                    BigDecimal rollover = TradingUtils.getRollover(garchTradingDto.openTime(), tickDto.timestamp(), symbol.swapLong(), symbol.swapShort(), PositionType.POSITION_TYPE_SELL);
                    BigDecimal points = TradingUtils.getPoints(garchTradingDto.sellOpenPrice(), tickDto.ask(), symbol.point(), PositionType.POSITION_TYPE_SELL).add(rollover);
                    if (newDealReason != null) {
                      sellTrading = Optional.of(new Trading(points, tickDto.bid(), newDealReason));
                    } else {
                      BigDecimal tpInPoints = TradingUtils.getPoints(garchTradingDto.sellOpenPrice(), garchTradingDto.sellTpPrice(), symbol.point(), PositionType.POSITION_TYPE_SELL);
                      if (tpInPoints.add(rollover).compareTo(BigDecimal.ZERO) <= 0) {
                        buyTrading = Optional.of(new Trading(points, tickDto.bid(), DealReason.DEAL_REASON_ROLLOVER));
                      }
                    }
                  }

                  return Mono.zip(Mono.just(buyTrading), Mono.just(sellTrading), Mono.just(garchTradingDto), Mono.just(tickDto));
                })
                .filter(objects -> objects.getT1().isPresent() || objects.getT2().isPresent())
                .sequential();
          }).concatMap(objects -> {
            Trading buyTrading = objects.getT1().isPresent() ? objects.getT1().get() : null;
            Trading sellTrading = objects.getT2().isPresent() ? objects.getT2().get() : null;
            GarchTradingDto garchTradingDto = objects.getT3();
            TickDto tickDto = objects.getT4();

            if (buyTrading != null && sellTrading != null) {
              log.debug("getGarchTradingScenarios(): buyTrading && sellTrading, creating Garch Trading [chartDto={}]", chartDto);
              return this.getGarchTradingService().updateTradingValues(chartDto, garchTradingDto.timestamp(), buyTrading.points(), buyTrading.closePrice(), tickDto.timestamp(), buyTrading.dealReason(), sellTrading.points(), sellTrading.closePrice(), tickDto.timestamp(), sellTrading.dealReason());
            }

            if (buyTrading != null) {
              log.debug("getGarchTradingScenarios(): buyTrading, creating Garch Trading [chartDto={}]", chartDto);
              return this.getGarchTradingService()
                  .updateTradingValues(chartDto, garchTradingDto.timestamp(), buyTrading.points(), buyTrading.closePrice(), tickDto.timestamp(), buyTrading.dealReason(), garchTradingDto.sellPoints(), garchTradingDto.closeSellPrice(), garchTradingDto.closeSellTime(), null);
            }

            if (sellTrading != null) {
              log.debug("getGarchTradingScenarios(): sellTrading, creating Garch Trading [chartDto={}]", chartDto);
              return this.getGarchTradingService()
                  .updateTradingValues(chartDto, garchTradingDto.timestamp(), garchTradingDto.buyPoints(), garchTradingDto.closeBuyPrice(), garchTradingDto.closeBuyTime(), null, sellTrading.points(), sellTrading.closePrice(), tickDto.timestamp(), sellTrading.dealReason());
            }

            return Mono.empty();
          })
          .collectList()
          .flatMap(garchTradingDtos -> garchTradingDtos.isEmpty() ? Mono.error(IllegalStateException::new) : this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_SCENARIO_COMPLETE))
          .onErrorResume(throwable -> {
            log.error("Error process Garch Tradings scenarios [name={}, period={}]: {}", chartDto.symbol().name(), chartDto.period(), throwable.getMessage());
            return this.getService().updateStatus(chartDto, DashboardStatus.GENERATING_GARCH_SCENARIO_ERROR).then(Mono.error(throwable));
          });
    };
  }

  private Function<DashboardDto, Mono<DashboardDto>> getGarchTradingScenariosExport() {
    return dashboardDto -> {
      ChartDto chartDto = dashboardDto.chartDto();
      SymbolDto symbol = chartDto.symbol();

      log.debug("getGarchTradingScenariosExport(): chartDto={}", chartDto);

      return this.getService().updateStatus(chartDto, DashboardStatus.GARCH_SCENARIOS_EXPORTING)
          .thenMany(this.getGarchTradingService().get(chartDto))
          .filter(garchTradingDto -> (garchTradingDto.buyDealReason() != null && (DealReason.DEAL_REASON_SL.equals(garchTradingDto.buyDealReason()) || DealReason.DEAL_REASON_TP.equals(garchTradingDto.buyDealReason()) || DealReason.DEAL_REASON_ROLLOVER.equals(garchTradingDto.buyDealReason()))) ||
              (garchTradingDto.sellDealReason() != null && (DealReason.DEAL_REASON_SL.equals(garchTradingDto.sellDealReason()) || DealReason.DEAL_REASON_TP.equals(garchTradingDto.sellDealReason()) || DealReason.DEAL_REASON_ROLLOVER.equals(garchTradingDto.sellDealReason())))
          )
          .flatMap(garchTradingDto -> {
            DealReason buyReason = garchTradingDto.buyDealReason();
            DealReason sellReason = garchTradingDto.sellDealReason();
            MLLabel mlLabel;
            if (buyReason != null && sellReason != null) {
              if (buyReason.equals(sellReason)) {
                if (DealReason.DEAL_REASON_ROLLOVER.equals(buyReason) || DealReason.DEAL_REASON_SL.equals(buyReason)) {
                  mlLabel = MLLabel.NEUTRAL;
                } else {
                  int compare = garchTradingDto.closeBuyTime().compareTo(garchTradingDto.closeSellTime());
                  mlLabel = ((compare < 0) ? MLLabel.BUY : ((compare > 0) ? MLLabel.SELL : MLLabel.NEUTRAL));
                }
              } else if (DealReason.DEAL_REASON_TP.equals(buyReason)) {
                mlLabel = MLLabel.BUY;
              } else if (DealReason.DEAL_REASON_TP.equals(sellReason)) {
                mlLabel = MLLabel.SELL;
              } else {
                mlLabel = MLLabel.NEUTRAL;
              }
            } else if (buyReason != null) {
              mlLabel = DealReason.DEAL_REASON_TP.equals(buyReason) ? MLLabel.BUY : MLLabel.NEUTRAL;
            } else {
              mlLabel = DealReason.DEAL_REASON_TP.equals(sellReason) ? MLLabel.SELL : MLLabel.NEUTRAL;
            }

            return Mono.zip(
                Mono.just(mlLabel),
                this.getGarchForecastService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                this.getCandlestickService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                Mono.zip(this.getAdxService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getAtrService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getBandsService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getMacdService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getMaFastService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getMaSlowService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getRsiService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList(),
                    this.getStochasticService().getLestThreeConsecutiveRecordsAsc(chartDto, garchTradingDto.timestamp()).collectList())
            );
          }).flatMap(objects -> {
            float mlLabel = objects.getT1().getValue();

            Stream<Float> garchForecastDtos = objects.getT2().stream().flatMap(garchForecastDto -> {
              float omega = garchForecastDto.omega().floatValue();
              float alpha = garchForecastDto.alpha().floatValue();
              float beta = garchForecastDto.beta().floatValue();
              float sigmaAgg = garchForecastDto.sigmaAgg().floatValue();

              return Stream.of(omega, alpha, beta, sigmaAgg);
            });

            Stream<Float> candlestickDtos = objects.getT3().stream().flatMap(candlestickDto -> {
              float type = 0f; // neutral 0f; bullish 1f; bearish 2f;
              float upperShadow = 0f;
              float lowShadow = 0f;
              float body = 0f;
              int compare = candlestickDto.open().compareTo(candlestickDto.close());
              if (compare < 0) {
                type = 1f;
                upperShadow = TradingUtils.getPoints(candlestickDto.high().subtract(candlestickDto.close()), symbol.point()).floatValue();
                lowShadow = TradingUtils.getPoints(candlestickDto.open().subtract(candlestickDto.low()), symbol.point()).floatValue();
                body = TradingUtils.getPoints(candlestickDto.close().subtract(candlestickDto.open()), symbol.point()).floatValue();
              } else if (compare > 0) {
                type = 2f;
                upperShadow = TradingUtils.getPoints(candlestickDto.high().subtract(candlestickDto.open()), symbol.point()).floatValue();
                lowShadow = TradingUtils.getPoints(candlestickDto.close().subtract(candlestickDto.low()), symbol.point()).floatValue();
                body = TradingUtils.getPoints(candlestickDto.open().subtract(candlestickDto.close()), symbol.point()).floatValue();
              } else {
                upperShadow = TradingUtils.getPoints(candlestickDto.high().subtract(candlestickDto.open()), symbol.point()).floatValue();
                lowShadow = TradingUtils.getPoints(candlestickDto.open().subtract(candlestickDto.low()), symbol.point()).floatValue();
              }

              return Stream.of(type, upperShadow, lowShadow, body);
            });

            Stream<Float> adxDtos = objects.getT4().getT1().stream().flatMap(adxDto -> {
              float mainLine = adxDto.mainLine().floatValue();
              float plusDiLine = adxDto.mainLine().floatValue();
              float minusDiLine = adxDto.mainLine().floatValue();

              return Stream.of(mainLine, plusDiLine, minusDiLine);
            });

            Stream<Float> atrDtos = objects.getT4().getT2().stream().map(atrDto -> atrDto.atr().floatValue());

            Stream<Float> bandsDtos = objects.getT4().getT3().stream().flatMap(bandsDto -> {
              float baseLine = bandsDto.baseLine().floatValue();
              float upperBand = bandsDto.upperBand().floatValue();
              float lowerBand = bandsDto.lowerBand().floatValue();

              return Stream.of(baseLine, upperBand, lowerBand);
            });

            Stream<Float> macdDtos = objects.getT4().getT4().stream().flatMap(macdDto -> {
              float mainLine = macdDto.mainLine().floatValue();
              float signalLine = macdDto.signalLine().floatValue();

              return Stream.of(mainLine, signalLine);
            });

            Stream<Float> maFastDtos = objects.getT4().getT5().stream().map(maFastDto -> maFastDto.ma().floatValue());
            Stream<Float> maSlowDtos = objects.getT4().getT6().stream().map(maSlowDto -> maSlowDto.ma().floatValue());
            Stream<Float> rsiDtos = objects.getT4().getT7().stream().map(rsiDto -> rsiDto.rsi().floatValue());

            Stream<Float> stochasticDtos = objects.getT4().getT8().stream().flatMap(stochasticDto -> {
              float mainLine = stochasticDto.mainLine().floatValue();
              float signalLine = stochasticDto.signalLine().floatValue();

              return Stream.of(mainLine, signalLine);
            });

            return Mono.just(Stream.of(Stream.of(mlLabel), garchForecastDtos, candlestickDtos, adxDtos, atrDtos, bandsDtos, macdDtos, maFastDtos, maSlowDtos, rsiDtos, stochasticDtos).flatMap(floatStream -> floatStream).toArray(Float[]::new));

          })
          .collectList()
          .publishOn(Schedulers.boundedElastic())
          .flatMap(matrix -> {
            log.info("DMatrix size: {}", matrix.size());

            if (matrix.isEmpty()) {
              return Mono.error(new IllegalStateException("A matriz de exportação está vazia."));
            }

            // Define o caminho do arquivo de saída (ex: dados_garch.train ou libsvm.txt)
            Path outputPath = Paths.get("dados_garch.train");

            try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8)) {
              for (Float[] row : matrix) {
                if (row == null || row.length == 0) {
                  continue;
                }

                StringBuilder line = new StringBuilder();

                // 1. O primeiro elemento (índice 0) é o Rótulo / Target (0.0, 1.0 ou 2.0)
                // Converte para inteiro (0, 1, 2) para o LIBSVM de classificação multiclasse
                int target = row[0].intValue();
                line.append(target);

                // 2. Transforma os elementos restantes em índices 1:valor 2:valor...
                for (int i = 1; i < row.length; i++) {
                  Float val = row[i];

                  // Omite valores nulos ou zerados (formato esparso LIBSVM)
                  if (val != null && Float.compare(val, 0.0f) != 0) {
                    // Formatação garantindo ponto (.) como separador decimal
                    line.append(" ")
                        .append(i)
                        .append(":")
                        .append(String.format(Locale.US, "%.6f", val));
                  }
                }

                // Escreve a linha no arquivo
                writer.write(line.toString());
                writer.newLine();
              }
              log.info("Arquivo LIBSVM gerado com sucesso em: {}", outputPath.toAbsolutePath());
            } catch (IOException e) {
              log.error("Erro ao escrever o arquivo LIBSVM: {}", e.getMessage(), e);
              return Mono.error(e);
            }

            return this.getService().updateStatus(chartDto, DashboardStatus.GARCH_SCENARIOS_EXPORTING_COMPLETE);
          });
    };
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping(path = "/generate_ml", produces = "application/json")
  public Mono<Void> generateML(@PathVariable @Pattern(regexp = "^[A-Z]{6}$") @Valid String name, @PathVariable @Valid Timeframe period) {
    log.info("Generate ML [name={}, period={}]", name, period);

    this.getChartDto(name, period).flatMap(chartDto -> this.getService().get(chartDto))
        // starting
        .flatMap(this.getGarchForecasts())
        // end Forecasts
        .flatMap(this.getGarchTradings())
        // end creating Trading
        .flatMap(this.getGarchTradingScenarios())
        // end processing Trading
        .flatMap(this.getGarchTradingScenariosExport())
        // end ML generate file input DMatrix
        //todo
        // end ML generate
        .subscribeOn(Schedulers.fromExecutor(this.getExecutor()))
        .subscribe(
            dashboardDto -> log.info("Generated ML [name={}, period={}] done: dashboardDto={}", name, period, dashboardDto),
            throwable -> log.error("Error to generated ML [name={}, period={}]", name, period, throwable)
        );

    return Mono.empty();
  }


}
