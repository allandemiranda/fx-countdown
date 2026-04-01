package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.ChartMapper;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.repository.ChartRepository;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class ChartService {

  private final ChartRepository repository;

  @Transactional(readOnly = true)
  public Mono<ChartDto> getChart(@NonNull SymbolDto symbolDto, Timeframe timeframe) {
    return this.getRepository().findChart(symbolDto.name(), timeframe).map(chart -> ChartMapper.toChartDto(symbolDto, chart));
  }

  @Transactional(readOnly = true)
  public Flux<ChartDto> getCharts(@NonNull SymbolDto symbolDto) {
    return this.getRepository().findCharts(symbolDto.name()).map(chart -> ChartMapper.toChartDto(symbolDto, chart));
  }

  public Mono<ChartDto> createChart(SymbolDto symbolDto, ChartCreateDto chartCreateDto) {
    Chart model = ChartMapper.toChart(UUID.randomUUID(), symbolDto, chartCreateDto);
    return this.getRepository().save(model).map(chart -> ChartMapper.toChartDto(symbolDto, chart));
  }

  public Mono<Void> deleteChart(@NonNull SymbolDto symbolDto, Timeframe period) {
    return this.getRepository().deleteChart(symbolDto.name(), period);
  }
}
