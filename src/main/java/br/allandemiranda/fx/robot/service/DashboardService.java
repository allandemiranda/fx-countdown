package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
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
public class DashboardService {

  private final DashboardRepository repository;
  private final DashboardMapper mapper;

  public Mono<DashboardDto> get(UUID id) {
    log.debug("Get [id={}]", id);
    return this.getRepository().findById(id).map(dashboard -> this.getMapper().toDto(dashboard));
  }

  public Flux<DashboardDto> get() {
    log.debug("Get");
    return this.getRepository().findAll().map(dashboard -> this.getMapper().toDto(dashboard));
  }

  public Mono<DashboardDto> create(DashboardCreateDto dashboardCreateDto) {
    log.debug("Create [dashboardCreateDto={}]", dashboardCreateDto);
    Dashboard model = this.getMapper().toModel(dashboardCreateDto);
    log.trace("Create [dashboardCreateDto={}], new object generated to save [dashboard={}]", dashboardCreateDto, model);
    return this.getRepository().save(model).map(dashboard -> this.getMapper().toDto(dashboard));
  }

  public Mono<Void> delete(DashboardDto dashboardDto) {
    log.debug("Delete [id={}]", dashboardDto);
    return this.getRepository().deleteById(dashboardDto.id());
  }
}
