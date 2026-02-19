package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
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
  @NotNull
  @Positive
  BigDecimal open;
  @NotNull
  @Positive
  BigDecimal high;
  @NotNull
  @Positive
  BigDecimal low;
  @NotNull
  @Positive
  BigDecimal close;
}