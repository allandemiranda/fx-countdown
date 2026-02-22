package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.service.StochasticService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@RestController
@RequestMapping("mt5/import/stochastics")
public class MT5ImportStochastic {

  private final StochasticService stochasticService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public StochasticDto createStochastic(@RequestBody @Valid StochasticCreateDto stochasticCreateDto) {
    return this.getStochasticService().create(stochasticCreateDto);
  }
}
