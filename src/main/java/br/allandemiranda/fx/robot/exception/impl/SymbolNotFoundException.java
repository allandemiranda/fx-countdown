package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested financial symbol cannot be found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class SymbolNotFoundException extends NotFoundException {

  /**
   * Constructs a new SymbolNotFoundException for the specified symbol ticker.
   *
   * @param name the missing symbol ticker
   */
  public SymbolNotFoundException(String name) {
    super("SymbolEntity not found: [" + name + "]");
  }
}
