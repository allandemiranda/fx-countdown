package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ExpertAdvisorNotFoundException extends NotFoundException {

  public ExpertAdvisorNotFoundException(String name) {
    super("ExpertAdvisor not found: [" + name + "]");
  }
}
