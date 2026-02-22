package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.CandlestickChartDto;
import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.TickChartDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.CandlestickChartMapper;
import br.allandemiranda.fx.robot.mapper.ChartCreateMapper;
import br.allandemiranda.fx.robot.mapper.ChartMapper;
import br.allandemiranda.fx.robot.mapper.TickChartMapper;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.repository.ChartRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class ChartService {

  private final ChartRepository repository;
  private final ChartMapper mapper;
  private final ChartCreateMapper createMapper;
  private final CandlestickChartMapper candlestickChartMapper;
  private final TickChartMapper tickChartMapper;

  public ChartDto create(ChartCreateDto chartCreateDto) {
    Chart entity = this.getCreateMapper().toEntity(chartCreateDto);
    Chart chart = this.getRepository().save(entity);
    return this.getMapper().toDto(chart);
  }

  @Transactional(readOnly = true)
  public Optional<ChartDto> getChart(String symbolName, Timeframe period) {
    return this.getChartEntity(symbolName, period).map(chart -> this.getMapper().toDto(chart));
  }

  @Transactional(readOnly = true)
  public Collection<ChartDto> getCharts() {
    return this.getRepository().findAll().stream().map(chart -> this.getMapper().toDto(chart)).collect(Collectors.toList());
  }

  private Optional<Chart> getChartEntity(String symbolName, Timeframe period) {
    return this.getRepository().findBySymbol_NameAndPeriod(symbolName, period);
  }

  @Transactional(readOnly = true)
  public Optional<CandlestickChartDto> getCandlesticks(String symbolName, Timeframe period) {
    return this.getChartEntity(symbolName, period).map(chart -> this.getCandlestickChartMapper().toDto(chart));
  }

  @Transactional(readOnly = true)
  public Optional<TickChartDto> getTicks(String symbolName, Timeframe period) {
    return this.getChartEntity(symbolName, period).map(chart -> this.getTickChartMapper().toDto(chart));
  }

}
