package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class CandlestickChartDto implements Serializable {

  UUID id;
  @NotNull
  CandlestickChartDto.SymbolDto symbol;
  @NotNull
  Timeframe period;
  @NotNull
  Set<CandlestickDto> candlesticks;
  @NotNull
  CandlestickChartDto.ScriptInfoDto scriptInfo;
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
   * DTO for {@link br.allandemiranda.fx.robot.model.Candlestick}
   */
  @Value
  public static class CandlestickDto implements Serializable {

    @NotNull
    UUID id;
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

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.ScriptInfo}
   */
  @Value
  public static class ScriptInfoDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    @PastOrPresent
    LocalDateTime updateTime;
    @NotNull
    @PastOrPresent
    LocalDateTime startScope;
    @NotNull
    @PastOrPresent
    LocalDateTime endScope;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IADXDto iAdx;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IBandsDto iBands;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IMADto iMaFast;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IMADto iMaSlow;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IATRDto iAtr;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IMACDDto iMacd;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IRSIDto iRsi;
    @NotNull
    CandlestickChartDto.ScriptInfoDto.IStochasticDto iStochastic;

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IADX}
     */
    @Value
    public static class IADXDto implements Serializable {

      short period;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IBands}
     */
    @Value
    public static class IBandsDto implements Serializable {

      short period;
      short shift;
      @NotNull
      @PositiveOrZero
      BigDecimal deviations;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IMA}
     */
    @Value
    public static class IMADto implements Serializable {

      short period;
      short shift;
      @NotNull
      SmoothingMethod method;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IMA}
     */
    @Value
    public static class IMADto implements Serializable {

      short period;
      short shift;
      @NotNull
      SmoothingMethod method;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IATR}
     */
    @Value
    public static class IATRDto implements Serializable {

      short period;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IMACD}
     */
    @Value
    public static class IMACDDto implements Serializable {

      short fastEma;
      short slowEma;
      short macdSma;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IRSI}
     */
    @Value
    public static class IRSIDto implements Serializable {

      short period;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IStochastic}
     */
    @Value
    public static class IStochasticDto implements Serializable {

      short kPeriod;
      short dPeriod;
      short slowing;
      @NotNull
      SmoothingMethod method;
      @NotNull
      PriceField priceField;
    }
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.ADX}
   */
  @Value
  public static class ADXDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal mainLine;
    @NotNull
    BigDecimal plusDiLine;
    @NotNull
    BigDecimal minusDiLine;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.Bands}
   */
  @Value
  public static class BandsDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal baseLine;
    @NotNull
    BigDecimal upperBand;
    @NotNull
    BigDecimal lowerBand;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.MaFast}
   */
  @Value
  public static class MaFastDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal ma;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.MaSlow}
   */
  @Value
  public static class MaSlowDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal ma;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.ATR}
   */
  @Value
  public static class ATRDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal value;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.MACD}
   */
  @Value
  public static class MACDDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    BigDecimal mainLine;
    @NotNull
    BigDecimal signalLine;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.RSI}
   */
  @Value
  public static class RSIDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    @Min(0)
    @Max(100)
    BigDecimal value;
  }

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.Stochastic}
   */
  @Value
  public static class StochasticDto implements Serializable {

    @NotNull
    UUID id;
    @NotNull
    ZonedDateTime timestamp;
    @NotNull
    @Min(0)
    @Max(100)
    BigDecimal mainLine;
    @NotNull
    @Min(0)
    @Max(100)
    BigDecimal signalLine;
  }
}