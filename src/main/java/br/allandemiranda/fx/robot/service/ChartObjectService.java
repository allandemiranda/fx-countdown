package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import br.allandemiranda.fx.robot.dto.impl.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.ChartObjectModel;
import br.allandemiranda.fx.robot.repository.ChartObjectRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChartObjectService<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  ChartObjectRepository<M> getRepository();

  ChartObjectMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(ChartDto chartDto, OffsetDateTime timestamp) {
    this.log().debug("Get [chartDto={}, timestamp={}]", chartDto, timestamp);
    return this.getRepository().findByChartIdAndTimestamp(chartDto.id(), timestamp).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Flux<D> get(ChartDto chartDto) {
    log().debug("Get [chartDto={}]", chartDto);
    return this.getRepository().findAllByChartIdOrderByTimestampAsc(chartDto.id()).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<D> create(ChartDto chartDto, C createDto) {
    this.log().debug("Create [chartDto={}, createDto={}]", chartDto, createDto);
    return this.get(chartDto, createDto.timestamp()).map(chartObjectDto -> {
      if (chartObjectDto instanceof CandlestickDto) {
        this.log().trace("Create [chartDto={}, createDto={}], object already exist [chartObjectDto={}]", chartDto, createDto, chartObjectDto);
      } else {
        this.log().warn("Create [chartDto={}, createDto={}], object already exist [chartObjectDto={}]", chartDto, createDto, chartObjectDto);
      }
      return chartObjectDto;
    }).switchIfEmpty(Mono.defer(() -> {
      M entity = this.getMapper().toModel(UUID.randomUUID(), chartDto, createDto);
      this.log().trace("Create [chartDto={}, createDto={}], new object generated to save [ChartObjectModel={}]", chartDto, createDto, entity);
      return this.getRepository().save(entity).map(model -> this.getMapper().toDto(chartDto, model));
    }));
  }

  default Mono<Void> delete(ChartDto chartDto) {
    this.log().debug("Delete [chartDto={}]", chartDto);
    return this.getRepository().deleteAllByChartId(chartDto.id());
  }

  default Flux<D> getLestThreeConsecutiveRecordsAsc(ChartDto chartDto, OffsetDateTime timestamp) {
    this.log().debug("Get Lest 3 [chartDto={}, timestamp={}]", chartDto, timestamp);
    return this.getRepository().getThreeConsecutiveRecordsAsc(chartDto.id(), timestamp).map(model -> this.getMapper().toDto(chartDto, model));
  }
}
