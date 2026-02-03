package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.InputObjectModel;
import br.allandemiranda.fx.robot.repository.impl.InputObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface InputObjectService<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  InputObjectRepository<M> getRepository();

  InputObjectMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(ChartDto chartDto) {
    this.log().debug("Get [chartDto={}]", chartDto);
    return this.getRepository().findByChartId(chartDto.id()).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<D> create(ChartDto chartDto, C createDto) {
    this.log().debug("Create [chartDto={}, createDto={}]", chartDto, createDto);
    M entity = this.getMapper().toModel(chartDto, createDto);
    this.log().trace("Create [chartDto={}, createDto={}], new object generated to save [InputObjectModel={}]", chartDto, createDto, entity);
    return this.getRepository().save(entity).map(model -> this.getMapper().toDto(chartDto, model));
  }

  default Mono<Void> delete(ChartDto chartDto) {
    this.log().debug("Delete [chartDto={}]", chartDto);
    return this.getRepository().deleteByChartId(chartDto.id());
  }
}
