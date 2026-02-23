package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Candlestick}
 */
@Value
public class CandlestickDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  @Positive
  double open;
  @Positive
  double high;
  @Positive
  double low;
  @Positive
  double close;
}