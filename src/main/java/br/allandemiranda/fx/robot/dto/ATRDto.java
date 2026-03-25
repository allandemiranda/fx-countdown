package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.model.AtrIndicator;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link AtrIndicator}
 */
@Value
public class ATRDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  double value;
}