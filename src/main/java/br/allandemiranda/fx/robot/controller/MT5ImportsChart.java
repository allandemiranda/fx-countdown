package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.ChartCreateDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.ChartNotFoundException;
import br.allandemiranda.fx.robot.service.ChartService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@RequestMapping("mt5/import/charts")
public class MT5ImportsChart {

  private final ChartService chartService;

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = "application/json")
  public Collection<ChartDto> getCharts() {
    return this.getChartService().getCharts();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/symbols/{name}/timeframes/{period}", produces = "application/json")
  public ChartDto getChart(@PathVariable @NotNull @NotEmpty @NotBlank @Valid String name, @PathVariable @NotNull @Valid Timeframe period) {
    return this.getChartService().getChart(name, period).orElseThrow(ChartNotFoundException::new);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public ChartDto createChart(@RequestBody @Valid ChartCreateDto chartCreateDto) {
    return this.getChartService().create(chartCreateDto);
  }

}
