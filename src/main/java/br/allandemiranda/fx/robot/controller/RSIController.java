package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.RSICreateDto;
import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.service.RSIService;
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
@RequestMapping("mt5/import/rsis")
public class RSIController {

  private final RSIService rsiService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public RSIDto createRSI(@RequestBody @Valid RSICreateDto rsiCreateDto) {
    return this.getRsiService().create(rsiCreateDto);
  }
}
