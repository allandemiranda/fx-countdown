package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Tick}
 */
@Value
public class TickDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  @Positive
  double ask;
  @Positive
  double bid;
}