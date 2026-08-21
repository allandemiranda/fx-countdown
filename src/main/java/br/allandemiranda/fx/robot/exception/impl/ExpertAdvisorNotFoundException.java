package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a requested Expert Advisor cannot be found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ExpertAdvisorNotFoundException extends NotFoundException {

  /**
   * Constructs a new ExpertAdvisorNotFoundException for the specified EA eaName.
   *
   * @param name the eaName of the missing EA
   */
  public ExpertAdvisorNotFoundException(String name) {
    super("ExpertAdvisor not found: [" + name + "]");
  }
}
