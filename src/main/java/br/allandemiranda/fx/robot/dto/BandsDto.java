package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Bands}
 */
@Value
public class BandsDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  double baseLine;
  double upperBand;
  double lowerBand;
}