package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Stochastic}
 */
@Value
public class StochasticCreateDto implements Serializable {

  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @NotEmpty
  @NotBlank
  String chartSymbolName;
  @NotNull
  Timeframe chartPeriod;
  @NotNull
  ZonedDateTime timestamp;
  @Min(0)
  @Max(100)
  double mainLine;
  @Min(0)
  @Max(100)
  double signalLine;
}