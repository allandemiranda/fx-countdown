package br.allandemiranda.fx.robot.exception;

import java.time.OffsetDateTime;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class TickNotFoundException extends RuntimeException {

  public TickNotFoundException(String symbolName, OffsetDateTime timestamp) {
    super("Tick not found: [" + symbolName + ", " + timestamp + "]");
  }
}
