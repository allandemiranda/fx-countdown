package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChartObjectService<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  ChartObjectRepository<M> getRepository();

  ChartObjectMapper<M, D, C> getMapper();

  default Mono<D> get(ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findByChartIdAndTimestamp(chartDto.id(), timestamp).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Flux<D> get(ChartDto chartDto) {
    return this.getRepository().findAllByChartId(chartDto.id()).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<D> create(ChartDto chartDto, C createDto) {
    return this.getRepository().save(this.getMapper().toModel(UUID.randomUUID(), chartDto, createDto)).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<Void> delete(ChartDto chartDto) {
    return this.getRepository().deleteAllByChartId(chartDto.id());
  }
}
