package br.allandemiranda.fx.robot.controller;

import br.allandemiranda.fx.robot.enums.Timeframe;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Log4j2
@Validated
@RestController
@RequestMapping("timeframes")
public class TimeframeController {

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<Timeframe> findAll() {
    log.debug("Find All");
    return Flux.fromStream(Arrays.stream(Timeframe.values()));
  }

}
