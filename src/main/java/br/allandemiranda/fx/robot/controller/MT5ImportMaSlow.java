package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.service.MaSlowService;
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
@RequestMapping("mt5/import/maSlows")
public class MT5ImportMaSlow {

  private final MaSlowService maSlowService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public MaSlowDto createMaSlow(@RequestBody @Valid MaSlowCreateDto maSlowCreateDto) {
    return this.getMaSlowService().create(maSlowCreateDto);
  }
}
