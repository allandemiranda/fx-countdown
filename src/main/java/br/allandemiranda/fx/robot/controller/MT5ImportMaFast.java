package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.service.MaFastService;
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
@RequestMapping("mt5/import/maFasts")
public class MT5ImportMaFast {

  private final MaFastService maFastService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public MaFastDto createMaFast(@RequestBody @Valid MaFastCreateDto maFastCreateDto) {
    return this.getMaFastService().create(maFastCreateDto);
  }
}
