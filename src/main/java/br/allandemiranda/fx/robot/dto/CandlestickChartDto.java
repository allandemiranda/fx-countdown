package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class CandlestickChartDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @NotEmpty
  @NotBlank
  String symbolName;
  @NotNull
  Timeframe period;
  @NotNull
  Set<CandlestickDto> candlesticks;
  @NotNull
  Set<ADXDto> timelineADX;
  @NotNull
  Set<BandsDto> timelineBands;
  @NotNull
  Set<MaFastDto> timelineMaFast;
  @NotNull
  Set<MaSlowDto> timelineMaSlow;
  @NotNull
  Set<ATRDto> timelineATR;
  @NotNull
  Set<MACDDto> timelineMACD;
  @NotNull
  Set<RSIDto> timelineRSI;
  @NotNull
  Set<StochasticDto> timelineStochastics;
}