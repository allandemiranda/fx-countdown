package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import java.time.OffsetDateTime;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class TickNotFoundException extends NotFoundException {

  public TickNotFoundException(String symbolName, OffsetDateTime timestamp) {
    super("Tick not found: [" + symbolName + ", " + timestamp + "]");
  }
}
