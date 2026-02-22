package br.allandemiranda.fx.robot.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ChartNotFoundException extends RuntimeException {

  public ChartNotFoundException(String name) {
    super("Chart not found: " + name);
  }
}
