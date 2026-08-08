package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.InputDashboardModel;
import br.allandemiranda.fx.robot.repository.InputDashboardRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface InputDashboardService<M extends InputDashboardModel, D extends InputDashboardDto, C extends InputDashboardCreateDto> {

  InputDashboardRepository<M> getRepository();

  InputDashboardMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(DashboardDto dashboardDto) {
    this.log().debug("Get [dashboardDto={}]", dashboardDto);
    return this.getRepository().findByDashboardId(dashboardDto.id()).map(model -> this.getMapper().toDto(dashboardDto, model));
  }

  default Mono<D> create(DashboardDto dashboardDto, C createDto) {
    this.log().debug("Create [dashboardDto={}, createDto={}]", dashboardDto, createDto);
    return this.get(dashboardDto).flatMap(inputDashboardDto -> {
      this.log().trace("Create [dashboardDto={}, createDto={}], object already exist [inputDashboardDto={}]", dashboardDto, createDto, inputDashboardDto);
      M model = this.getMapper().toModel(inputDashboardDto.id(), dashboardDto, createDto);
      return this.getRepository().save(model).map(inputDashboard -> this.getMapper().toDto(dashboardDto, inputDashboard));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), dashboardDto, createDto);
      this.log().trace("Create [dashboardDto={}, createDto={}], new object generated to save [inputDashboard={}]", dashboardDto, createDto, model);
      return this.getRepository().save(model).map(inputDashboard -> this.getMapper().toDto(dashboardDto, inputDashboard));
    }));
  }

  default Mono<Void> delete(DashboardDto dashboardDto) {
    this.log().debug("Delete [dashboardDto={}]", dashboardDto);
    return this.getRepository().deleteByDashboardId(dashboardDto.id());
  }
}
