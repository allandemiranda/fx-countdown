package br.allandemiranda.fx.robot.exception;

import br.allandemiranda.fx.robot.enums.Timeframe;
import java.time.OffsetDateTime;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ChartObjectNotFoundException extends RuntimeException {

  public ChartObjectNotFoundException(String symbolName, Timeframe period, String objectName, OffsetDateTime timestamp) {
    super("Chart object not found: [" + symbolName + ", " + period + ", " + objectName + ", " + timestamp + "]");
  }
}
