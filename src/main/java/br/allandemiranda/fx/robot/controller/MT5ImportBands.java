package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.dto.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.service.BandsService;
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
@RequestMapping("mt5/import/bands")
public class MT5ImportBands {

  private final BandsService bandsService;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = "application/json")
  public BandsDto createBands(@RequestBody @Valid BandsCreateDto bandsCreateDto) {
    return this.getBandsService().create(bandsCreateDto);
  }
}
