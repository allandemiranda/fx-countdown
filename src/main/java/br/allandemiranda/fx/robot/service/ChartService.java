package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.ChartMapper;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.repository.ChartRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
@Service
public class ChartService {

  private final ChartRepository repository;
  private final ChartMapper mapper;

  public Mono<ChartDto> get(SymbolDto symbolDto, Timeframe period) {
    return this.getRepository().findBySymbolNameAndPeriod(symbolDto.name(), period).map(chart -> this.getMapper().toDto(symbolDto, chart));
  }

  public Flux<ChartDto> get(SymbolDto symbolDto) {
    return this.getRepository().findBySymbolName(symbolDto.name()).map(chart -> this.getMapper().toDto(symbolDto, chart));
  }

  public Mono<ChartDto> create(SymbolDto symbolDto, ChartCreateDto chartCreateDto) {
    Chart model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, chartCreateDto);
    return this.getRepository().save(model).map(chart -> this.getMapper().toDto(symbolDto, chart));
  }

  public Mono<Void> delete(SymbolDto symbolDto, Timeframe period) {
    return this.getRepository().deleteAllBySymbolNameAndPeriod(symbolDto.name(), period);
  }
}
