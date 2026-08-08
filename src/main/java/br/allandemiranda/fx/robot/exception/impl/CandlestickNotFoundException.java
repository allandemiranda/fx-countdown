package br.allandemiranda.fx.robot.exception.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.NotFoundException;
import java.time.OffsetDateTime;
import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public class CandlestickNotFoundException extends NotFoundException {

  public CandlestickNotFoundException(String symbolName, Timeframe timeframe, OffsetDateTime timestamp) {
    super("Candlestick not found: [" + symbolName + ", " + timeframe.getCode() + ", " + timestamp + "]");
  }
}
