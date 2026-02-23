package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Stochastic}
 */
@Value
public class StochasticDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  @Min(0)
  @Max(100)
  double mainLine;
  @Min(0)
  @Max(100)
  double signalLine;
}