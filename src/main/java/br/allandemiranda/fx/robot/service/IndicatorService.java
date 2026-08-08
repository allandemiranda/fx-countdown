package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import br.allandemiranda.fx.robot.repository.IndicatorRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IndicatorService<M extends IndicatorModel, D extends IndicatorDto, C extends IndicatorCreateDto> {

  IndicatorRepository<M> getRepository();

  IndicatorMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(DashboardDto dashboardDto, OffsetDateTime timestamp) {
    this.log().debug("Get [dashboardDto={}, timestamp={}]", dashboardDto, timestamp);
    return this.getRepository().findByDashboardIdAndTimestamp(dashboardDto.id(), timestamp).map(model -> this.getMapper().toDto(dashboardDto, model));
  }

  default Flux<D> get(DashboardDto dashboardDto) {
    log().debug("Get [dashboardDto={}]", dashboardDto);
    return this.getRepository().findAllByDashboardIdAsc(dashboardDto.id()).map(model -> this.getMapper().toDto(dashboardDto, model));
  }

  default Mono<D> create(DashboardDto dashboardDto, C createDto) {
    this.log().debug("Create [dashboardDto={}, createDto={}]", dashboardDto, createDto);
    return this.get(dashboardDto, createDto.timestamp()).flatMap(indicatorDto -> {
      this.log().warn("Create [dashboardDto={}, createDto={}], object already exist [indicatorDto={}]", dashboardDto, createDto, indicatorDto);
      M model = this.getMapper().toModel(indicatorDto.id(), dashboardDto, createDto);
      this.log().trace("Create [dashboardDto={}, createDto={}], updating already exist [indicator={}]", dashboardDto, createDto, model);
      return this.getRepository().save(model).map(indicator -> this.getMapper().toDto(dashboardDto, indicator));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), dashboardDto, createDto);
      this.log().trace("Create [dashboardDto={}, createDto={}], new object generated to save [indicator={}]", dashboardDto, createDto, model);
      return this.getRepository().save(model).map(indicator -> this.getMapper().toDto(dashboardDto, indicator));
    }));
  }

  default Mono<Void> delete(DashboardDto dashboardDto) {
    this.log().debug("Delete [dashboardDto={}]", dashboardDto);
    return this.getRepository().deleteAllByDashboardId(dashboardDto.id());
  }
}
