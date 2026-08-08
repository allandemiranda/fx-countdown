package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.controller.util.DashboardUtils;
import br.allandemiranda.fx.robot.dto.DashboardCreateDto;
import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.service.DashboardService;
import br.allandemiranda.fx.robot.service.impl.indicator.ADXService;
import br.allandemiranda.fx.robot.service.impl.indicator.ATRService;
import br.allandemiranda.fx.robot.service.impl.indicator.BandsService;
import br.allandemiranda.fx.robot.service.impl.indicator.MACDService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaFastService;
import br.allandemiranda.fx.robot.service.impl.indicator.MaSlowService;
import br.allandemiranda.fx.robot.service.impl.indicator.RSIService;
import br.allandemiranda.fx.robot.service.impl.indicator.StochasticService;
import br.allandemiranda.fx.robot.service.impl.input.GarchInputService;
import br.allandemiranda.fx.robot.service.impl.input.IADXService;
import br.allandemiranda.fx.robot.service.impl.input.IATRService;
import br.allandemiranda.fx.robot.service.impl.input.IBandsService;
import br.allandemiranda.fx.robot.service.impl.input.IMACDService;
import br.allandemiranda.fx.robot.service.impl.input.IMAFastService;
import br.allandemiranda.fx.robot.service.impl.input.IMASlowService;
import br.allandemiranda.fx.robot.service.impl.input.IRSIService;
import br.allandemiranda.fx.robot.service.impl.input.IStochasticService;
import br.allandemiranda.fx.robot.service.impl.input.XGBoostInputService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("dashboards")
public class DashboardController {

  private final DashboardService dashboardService;

  private final ADXService adxService;
  private final ATRService atrService;
  private final MACDService macdService;
  private final RSIService rsiService;
  private final BandsService bandsService;
  private final MaFastService maFastService;
  private final MaSlowService maSlowService;
  private final StochasticService stochasticService;

  private final IADXService iadxService;
  private final IATRService iatrService;
  private final IBandsService ibandsService;
  private final IMACDService imacdService;
  private final IMAFastService imaFastService;
  private final IMASlowService imaSlowService;
  private final IRSIService irsiService;
  private final IStochasticService iStochasticService;
  private final XGBoostInputService xgBoostInputService;
  private final GarchInputService garchInputService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Flux<DashboardDto> findAll() {
    log.debug("Find All");
    return this.getDashboardService().get();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{id}", produces = "application/json")
  public Mono<DashboardDto> find(@PathVariable UUID id) {
    log.debug("Find [id={}]", id);
    return DashboardUtils.getDashboard(id, this.getDashboardService()).doOnError(throwable -> log.warn("Trouble for finding dashboard", throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public Mono<DashboardDto> create(@RequestBody @Valid DashboardCreateDto dashboardCreateDto) {
    log.debug("Create [dashboardCreateDto={}]", dashboardCreateDto);
    return this.getDashboardService().create(dashboardCreateDto).doOnError(throwable -> log.warn("Trouble for creating dashboard", throwable)).switchIfEmpty(Mono.defer(() -> {
      log.warn("Error creating dashboard: create returned empty dashboard");
      return Mono.error(IllegalStateException::new);
    }));
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{id}", produces = "application/json")
  public Mono<Void> delete(@PathVariable UUID id) {
    log.debug("Delete [name={}]", id);
    return DashboardUtils.getDashboard(id, this.getDashboardService())
        .flatMap(dashboardDto -> this.getDashboardService().delete(dashboardDto)
            .then(this.getAdxService().delete(dashboardDto))
            .then(this.getAtrService().delete(dashboardDto))
            .then(this.getMacdService().delete(dashboardDto))
            .then(this.getRsiService().delete(dashboardDto))
            .then(this.getBandsService().delete(dashboardDto))
            .then(this.getMaFastService().delete(dashboardDto))
            .then(this.getMaSlowService().delete(dashboardDto))
            .then(this.getStochasticService().delete(dashboardDto))
            .then(this.getIadxService().delete(dashboardDto))
            .then(this.getIatrService().delete(dashboardDto))
            .then(this.getIbandsService().delete(dashboardDto))
            .then(this.getImacdService().delete(dashboardDto))
            .then(this.getImaFastService().delete(dashboardDto))
            .then(this.getImaSlowService().delete(dashboardDto))
            .then(this.getIrsiService().delete(dashboardDto))
            .then(this.getIStochasticService().delete(dashboardDto))
            .then(this.getXgBoostInputService().delete(dashboardDto))
            .then(this.getGarchInputService().delete(dashboardDto))
        )
        .doOnError(throwable -> log.warn("Trouble for deleting dashboard", throwable));
  }
}
