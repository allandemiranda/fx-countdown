package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.ScriptInfo}
 */
@Value
public class ScriptInfoDto implements Serializable {

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
  ScriptInfoDto.IADXDto iAdx;
  @NotNull
  ScriptInfoDto.IBandsDto iBands;
  @NotNull
  ScriptInfoDto.IMAFastDto iMaFast;
  @NotNull
  ScriptInfoDto.IMASlowDto iMaSlow;
  @NotNull
  ScriptInfoDto.IATRDto iAtr;
  @NotNull
  ScriptInfoDto.IMACDDto iMacd;
  @NotNull
  ScriptInfoDto.IRSIDto iRsi;
  @NotNull
  ScriptInfoDto.IStochasticDto iStochastic;

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