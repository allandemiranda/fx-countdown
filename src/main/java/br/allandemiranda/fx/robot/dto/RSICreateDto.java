package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.RSI}
 */
@Value
public class RSICreateDto implements Serializable {

  String chartSymbolName;
  Timeframe chartPeriod;
  @NotNull
  ZonedDateTime timestamp;
  @NotNull
  @Min(0)
  @Max(100)
  BigDecimal value;
}