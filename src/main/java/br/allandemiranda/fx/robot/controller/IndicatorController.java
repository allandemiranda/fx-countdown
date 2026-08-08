package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.DashboardUtils;
import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import br.allandemiranda.fx.robot.service.DashboardService;
import br.allandemiranda.fx.robot.service.IndicatorService;
import jakarta.validation.Valid;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
public interface IndicatorController<M extends IndicatorModel, D extends IndicatorDto, C extends IndicatorCreateDto> {

  IndicatorService<M, D, C> getService();

  DashboardService getDashboardService();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  default Flux<D> findAll(@PathVariable UUID dashboardId) {
    this.log().debug("Find All [dashboardId={}]", dashboardId);
    return DashboardUtils.getDashboard(dashboardId, this.getDashboardService()).flatMapMany(dashboardDto -> this.getService().get(dashboardDto))
        .doOnError(throwable -> this.log().warn("Trouble for finding all indicators", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  default Mono<D> create(@PathVariable UUID dashboardId, @RequestBody @Valid C indicatorCreateDto) {
    this.log().debug("Create [dashboardId={}, indicatorCreateDto={}]", dashboardId, indicatorCreateDto);
    return DashboardUtils.getDashboard(dashboardId, this.getDashboardService()).flatMap(dashboardDto -> this.getService().create(dashboardDto, indicatorCreateDto))
        .doOnError(throwable -> log().warn("Trouble for creating indicator", throwable)).switchIfEmpty(Mono.defer(() -> {
          log().warn("Error creating indicator: create returned empty indicator");
          return Mono.error(IllegalStateException::new);
        }));
  }

}
