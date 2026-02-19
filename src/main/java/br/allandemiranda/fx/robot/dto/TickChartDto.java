package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class TickChartDto implements Serializable {

  UUID id;
  @NotNull
  TickChartDto.SymbolDto symbol;
  @NotNull
  Timeframe period;
  @NotNull
  Set<TickDto> ticks;

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.Symbol}
   */
  @Value
  public static class SymbolDto implements Serializable {

    @NotNull
    @Size(min = 6, max = 6)
    @Pattern(regexp = "^[A-Z]{6}$")
    @NotEmpty
    @NotBlank
    String name;
    @NotNull
    @Positive
    BigDecimal point;
    @NotNull
    BigDecimal swapLong;
    @NotNull
    BigDecimal swapShort;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.Tick}
   */
  @Value
  public static class TickDto implements Serializable {

    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal ask;
    @NotNull
    BigDecimal bid;
  }
}