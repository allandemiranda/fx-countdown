package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.service.CandlestickService;
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
@RequestMapping("mt5/import/candlesticks")
public class CandlestickController {

  private final CandlestickService candlestickService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public CandlestickDto createCandlestick(@RequestBody @Valid CandlestickCreateDto candlestickCreateDto) {
    return this.getCandlestickService().create(candlestickCreateDto);
  }
}
