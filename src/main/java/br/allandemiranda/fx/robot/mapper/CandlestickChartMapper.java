package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.CandlestickChartDto;
import br.allandemiranda.fx.robot.model.Chart;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING, uses = {CandlestickMapper.class, ADXMapper.class, BandsMapper.class, MaFastMapper.class, MaSlowMapper.class, ATRMapper.class, MACDMapper.class, RSIMapper.class, StochasticMapper.class})
public interface CandlestickChartMapper {

  @Mapping(source = "symbolName", target = "symbol.name")
  Chart toEntity(CandlestickChartDto candlestickChartDto);

  @AfterMapping
  default void linkCandlesticks(@MappingTarget Chart chart) {
    chart.getCandlesticks().forEach(candlestick -> candlestick.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineADX(@MappingTarget Chart chart) {
    chart.getTimelineADX().forEach(timelineADX -> timelineADX.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineBands(@MappingTarget Chart chart) {
    chart.getTimelineBands().forEach(timelineBand -> timelineBand.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineMaFast(@MappingTarget Chart chart) {
    chart.getTimelineMaFast().forEach(timelineMaFast -> timelineMaFast.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineMaSlow(@MappingTarget Chart chart) {
    chart.getTimelineMaSlow().forEach(timelineMaSlow -> timelineMaSlow.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineATR(@MappingTarget Chart chart) {
    chart.getTimelineATR().forEach(timelineATR -> timelineATR.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineMACD(@MappingTarget Chart chart) {
    chart.getTimelineMACD().forEach(timelineMACD -> timelineMACD.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineRSI(@MappingTarget Chart chart) {
    chart.getTimelineRSI().forEach(timelineRSI -> timelineRSI.setChart(chart));
  }

  @AfterMapping
  default void linkTimelineStochastics(@MappingTarget Chart chart) {
    chart.getTimelineStochastics().forEach(timelineStochastic -> timelineStochastic.setChart(chart));
  }

  @Mapping(source = "symbol.name", target = "symbolName")
  CandlestickChartDto toDto(Chart chart);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(source = "symbolName", target = "symbol.name")
  Chart partialUpdate(CandlestickChartDto candlestickChartDto, @MappingTarget Chart chart);
}