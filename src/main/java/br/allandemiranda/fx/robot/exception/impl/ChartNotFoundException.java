package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.NotFoundException;
import lombok.experimental.StandardException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class ChartNotFoundException extends NotFoundException {

  public ChartNotFoundException(String symbolName, @NonNull Timeframe period) {
    super("Chart not found: [" + symbolName + ", " + period.getCode() + "]");
  }
}
