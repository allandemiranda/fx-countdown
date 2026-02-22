package br.allandemiranda.fx.robot.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class SymbolNotFoundException extends RuntimeException {

  public SymbolNotFoundException(String name) {
    super("Symbol not found: " + name);
  }
}
