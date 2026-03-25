package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.dto.CandlestickChartDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.MACDDto;
import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.dto.TickChartDto;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.repository.LMDataBuildRepository;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class LMDataBuildService {

  private final LMDataBuildRepository repository;
  private final Executor executor;

  public void createLMDataBuild(DashboardDto dashboardDto, CandlestickChartDto candlestickChartDto, TickChartDto tickChartDto) {
    ZonedDateTime olderData = this.olderDataTimeValid(candlestickChartDto, tickChartDto);


  }

  private ZonedDateTime olderDataTimeValid(CandlestickChartDto candlestickChartDto, TickChartDto tickChartDto) {
    ZonedDateTime candlestickDto = candlestickChartDto.getCandlesticks().stream().min(Comparator.comparing(CandlestickDto::getTimestamp)).map(CandlestickDto::getTimestamp).orElseThrow();
    ZonedDateTime adxDto = candlestickChartDto.getTimelineADX().stream().min(Comparator.comparing(ADXDto::getTimestamp)).map(ADXDto::getTimestamp).orElseThrow();
    ZonedDateTime bandsDto = candlestickChartDto.getTimelineBands().stream().min(Comparator.comparing(BandsDto::getTimestamp)).map(BandsDto::getTimestamp).orElseThrow();
    ZonedDateTime maFastDto = candlestickChartDto.getTimelineMaFast().stream().min(Comparator.comparing(MaFastDto::getTimestamp)).map(MaFastDto::getTimestamp).orElseThrow();
    ZonedDateTime maSlowDto = candlestickChartDto.getTimelineMaSlow().stream().min(Comparator.comparing(MaSlowDto::getTimestamp)).map(MaSlowDto::getTimestamp).orElseThrow();
    ZonedDateTime atrDto = candlestickChartDto.getTimelineATR().stream().min(Comparator.comparing(ATRDto::getTimestamp)).map(ATRDto::getTimestamp).orElseThrow();
    ZonedDateTime macdDto = candlestickChartDto.getTimelineMACD().stream().min(Comparator.comparing(MACDDto::getTimestamp)).map(MACDDto::getTimestamp).orElseThrow();
    ZonedDateTime rsiDto = candlestickChartDto.getTimelineRSI().stream().min(Comparator.comparing(RSIDto::getTimestamp)).map(RSIDto::getTimestamp).orElseThrow();
    ZonedDateTime stochasticDto = candlestickChartDto.getTimelineStochastics().stream().min(Comparator.comparing(StochasticDto::getTimestamp)).map(StochasticDto::getTimestamp).orElseThrow();

    ZonedDateTime tickDto = tickChartDto.getTicks().stream().min(Comparator.comparing(TickDto::getTimestamp)).map(TickDto::getTimestamp).orElseThrow();

    return Stream.of(candlestickDto, adxDto, bandsDto, maFastDto, maSlowDto, atrDto, macdDto, rsiDto, stochasticDto, tickDto).max(ChronoZonedDateTime::compareTo).orElseThrow();
  }

}
