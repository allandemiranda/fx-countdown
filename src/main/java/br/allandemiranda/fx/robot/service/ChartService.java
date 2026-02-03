package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.create.ChartCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.ChartMapper;
import br.allandemiranda.fx.robot.model.Chart;
import br.allandemiranda.fx.robot.repository.ChartRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Service
public class ChartService {

  private final ChartRepository repository;
  private final ChartMapper mapper;

  public Mono<ChartDto> get(SymbolDto symbolDto, Timeframe period) {
    log.debug("Get [symbolDto={}, period={}]", symbolDto, period);
    return this.getRepository().findBySymbolNameAndPeriod(symbolDto.name(), period).map(chart -> this.getMapper().toDto(symbolDto, chart));
  }

  public Flux<ChartDto> get(SymbolDto symbolDto) {
    log.debug("Get [symbolDto={}]", symbolDto);
    return this.getRepository().findBySymbolName(symbolDto.name()).map(chart -> this.getMapper().toDto(symbolDto, chart));
  }

  public Mono<ChartDto> create(SymbolDto symbolDto, ChartCreateDto chartCreateDto) {
    log.debug("Create [symbolDto={}, chartCreateDto={}]", symbolDto, chartCreateDto);
    return this.get(symbolDto, chartCreateDto.period()).map(chartDto -> {
      log.trace("Create [symbolDto={}, chartCreateDto={}], object already exist [chartDto={}]", symbolDto, chartCreateDto, chartDto);
      return chartDto;
    }).switchIfEmpty(Mono.defer(() -> {
      Chart model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, chartCreateDto);
      log.trace("Create [symbolDto={}, chartCreateDto={}], new object generated to save [chart={}]", symbolDto, chartCreateDto, model);
      return this.getRepository().save(model).map(chart -> this.getMapper().toDto(symbolDto, chart));
    }));
  }

  public Mono<Void> delete(ChartDto chartDto) {
    log.debug("Delete [chartDto={}]", chartDto);
    return this.getRepository().deleteBySymbolNameAndPeriod(chartDto.symbol().name(), chartDto.period());
  }
}
