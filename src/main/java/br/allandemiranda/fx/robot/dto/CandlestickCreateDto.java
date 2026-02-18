package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Candlestick}
 */
@Value
public class CandlestickCreateDto implements Serializable {

  String chartSymbolName;
  Timeframe chartPeriod;
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