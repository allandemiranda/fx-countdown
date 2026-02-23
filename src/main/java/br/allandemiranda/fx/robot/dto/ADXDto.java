package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.ADX}
 */
@Value
public class ADXDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ZonedDateTime timestamp;
  double mainLine;
  double plusDiLine;
  double minusDiLine;
}