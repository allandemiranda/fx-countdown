package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Tick}
 */
@Value
public class TickCreateDto implements Serializable {

  @NotNull
  ZonedDateTime timestamp;
  @NotNull
  BigDecimal ask;
  @NotNull
  BigDecimal bid;
}