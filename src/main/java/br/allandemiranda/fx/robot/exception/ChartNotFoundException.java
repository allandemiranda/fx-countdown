package br.allandemiranda.fx.robot.exception;

import br.allandemiranda.fx.robot.enums.Timeframe;
import lombok.experimental.StandardException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ChartNotFoundException extends RuntimeException {

  public ChartNotFoundException(String symbolName, @NonNull Timeframe period) {
    super("Chart not found: [" + symbolName + ", " + period.getCode() + "]");
  }
}
