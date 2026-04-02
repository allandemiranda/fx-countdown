package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.repository.contract.InputObjectRepository;
import reactor.core.publisher.Mono;

public interface InputObjectService<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto, R extends InputObjectRepository<M>> {

  R getRepository();

  InputObjectMapper<M, D, C> getMapper();

  default Mono<D> get(ChartDto chartDto) {
    return this.getRepository().findByChartId(chartDto.id()).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<D> create(ChartDto chartDto, C createDto) {
    return this.getRepository().save(this.getMapper().toModel(chartDto, createDto)).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<Void> delete(ChartDto chartDto) {
    return this.getRepository().deleteByChartId(chartDto.id());
  }
}
