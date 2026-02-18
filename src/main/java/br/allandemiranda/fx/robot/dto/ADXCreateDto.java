package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.ADX}
 */
@Value
public class ADXCreateDto implements Serializable {

  String chartSymbolName;
  Timeframe chartPeriod;
  @NotNull
  ZonedDateTime timestamp;
  @NotNull
  BigDecimal mainLine;
  @NotNull
  BigDecimal plusDiLine;
  @NotNull
  BigDecimal minusDiLine;
}