package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.service.ADXService;
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
@RequestMapping("mt5/import/adxs")
public class ADXController {

  private final ADXService adxService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public ADXDto createADX(@RequestBody @Valid ADXCreateDto adxCreateDto) {
    return this.getAdxService().create(adxCreateDto);
  }
}
