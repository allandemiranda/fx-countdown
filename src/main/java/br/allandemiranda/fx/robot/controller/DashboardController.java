package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.service.DashboardService;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@RequestMapping("ml/dashboard")
public class DashboardController {

  private final DashboardService dashboardService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Collection<DashboardDto> getDashboards() {
    return this.getDashboardService().getDashboard();
  }

}
