package br.allandemiranda.fx.robot.util;

import br.allandemiranda.fx.robot.dto.TimeLineObjectDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchForecastCreateDto;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

@UtilityClass
public class DashboardUtils {

  private static <T extends TimeLineObjectDto> Function<Flux<T>, Mono<Tuple2<OffsetDateTime, OffsetDateTime>>> getRealScopeTime(OffsetDateTime startScope, OffsetDateTime endScope) {
    return timeLineObjectFlux -> timeLineObjectFlux.filter(timeLineObject -> (timeLineObject.timestamp().isAfter(startScope) || timeLineObject.timestamp().isEqual(startScope)) && (timeLineObject.timestamp().isBefore(endScope) || timeLineObject.timestamp().isEqual(endScope)))
        .collectList().flatMap(chartObjects -> {
          if (chartObjects.isEmpty()) {
            return Mono.error(IllegalStateException::new);
          } else {
            TimeLineObjectDto timeLineObjectFirst = chartObjects.getFirst();
            TimeLineObjectDto timeLineObjectLast = chartObjects.getLast();
            return Mono.zip(Mono.just(timeLineObjectFirst.timestamp()), Mono.just(timeLineObjectLast.timestamp()));
          }
        });
  }

  public static <T extends TimeLineObjectDto> Mono<Tuple2<OffsetDateTime, OffsetDateTime>> getRealScopeTime(DashboardDto dashboardDto, Flux<T> timeLineObjects) {
    return DashboardUtils.getRealScopeTime(dashboardDto.startScope(), dashboardDto.endScope()).apply(timeLineObjects.cast(TimeLineObjectDto.class));
  }

  private static GarchForecastCreateDto createGarchForecastCreateDto(List<CandlestickDto> candlestickList, int horizon) {
    double[] price = candlestickList.stream().mapToDouble(candlestick -> candlestick.close().doubleValue()).toArray();
    Garch11ModuleUtils.GarchForecast garchForecast = Garch11ModuleUtils.fitAndForecast(price, horizon);
    CandlestickDto lastCandlestick = candlestickList.getLast();

    return new GarchForecastCreateDto(lastCandlestick.timestamp(), BigDecimal.valueOf(garchForecast.omega()), BigDecimal.valueOf(garchForecast.alpha()), BigDecimal.valueOf(garchForecast.beta()), BigDecimal.valueOf(garchForecast.sigmaAgg()));
  }

  public static Flux<GarchForecastCreateDto> getGarchForecastCreateDtoFlux(Flux<CandlestickDto> candlesticks, DashboardDto dashboardDto, GarchInputDto garchInputDto) {
    return candlesticks.filter(
            candlestickDto -> (candlestickDto.timestamp().isAfter(dashboardDto.startScope()) || candlestickDto.timestamp().isEqual(dashboardDto.startScope())) && (candlestickDto.timestamp().isBefore(dashboardDto.endScope()) || candlestickDto.timestamp().isEqual(dashboardDto.endScope())))
        .collectList()
        .flatMapMany(candlestickDtos -> ((candlestickDtos.size() - garchInputDto.priceSize() + 1) <= 0) ? Flux.empty() : Flux.range(0, candlestickDtos.size() - garchInputDto.priceSize() + 1).parallel().runOn(Schedulers.parallel()).map(i -> candlestickDtos.subList(i, i + garchInputDto.priceSize()))
            .map(candlestickList -> DashboardUtils.createGarchForecastCreateDto(candlestickList, garchInputDto.horizon())).sequential());
  }

}
