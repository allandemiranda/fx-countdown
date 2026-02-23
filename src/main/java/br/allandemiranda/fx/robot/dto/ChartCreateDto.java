package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class ChartCreateDto implements Serializable {

  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @NotEmpty
  @NotBlank
  String symbolName;
  @NotNull
  Timeframe period;
  @NotNull
  ChartCreateDto.ScriptInfoDto scriptInfo;

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.ScriptInfo}
   */
  @Value
  public static class ScriptInfoDto implements Serializable {

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
    ChartCreateDto.ScriptInfoDto.IADXDto iAdx;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IBandsDto iBands;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IMAFastDto iMaFast;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IMASlowDto iMaSlow;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IATRDto iAtr;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IMACDDto iMacd;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IRSIDto iRsi;
    @NotNull
    ChartCreateDto.ScriptInfoDto.IStochasticDto iStochastic;

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
      @PositiveOrZero
      double deviations;
      @NotNull
      AppliedPrice applyTo;
    }

    /**
     * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.IMA}
     */
    @Value
    public static class IMAFastDto implements Serializable {

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
    public static class IMASlowDto implements Serializable {

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
}