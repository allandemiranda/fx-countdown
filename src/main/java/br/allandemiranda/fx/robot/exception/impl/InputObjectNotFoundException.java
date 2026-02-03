package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.NotFoundException;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class InputObjectNotFoundException extends NotFoundException {

  public InputObjectNotFoundException(String symbolName, Timeframe period, String objectName) {
    super("Input object not found: [" + symbolName + ", " + period + ", " + objectName + "]");
  }
}
