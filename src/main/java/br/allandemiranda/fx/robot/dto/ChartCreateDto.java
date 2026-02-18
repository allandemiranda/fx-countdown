package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class ChartCreateDto implements Serializable {

  String symbolName;
  @NotNull
  Timeframe period;
  LocalDateTime scriptInfoUpdateTime;
  LocalDateTime scriptInfoStartScope;
  LocalDateTime scriptInfoEndScope;
  short scriptInfoIAdxPeriod;
  short scriptInfoIBandsPeriod;
  short scriptInfoIBandsShift;
  BigDecimal scriptInfoIBandsDeviations;
  AppliedPrice scriptInfoIBandsApplyTo;
  short scriptInfoIMaFastPeriod;
  short scriptInfoIMaFastShift;
  SmoothingMethod scriptInfoIMaFastMethod;
  AppliedPrice scriptInfoIMaFastApplyTo;
  short scriptInfoIMaSlowPeriod;
  short scriptInfoIMaSlowShift;
  SmoothingMethod scriptInfoIMaSlowMethod;
  AppliedPrice scriptInfoIMaSlowApplyTo;
  short scriptInfoIAtrPeriod;
  short scriptInfoIMacdFastEma;
  short scriptInfoIMacdSlowEma;
  short scriptInfoIMacdMacdSma;
  AppliedPrice scriptInfoIMacdApplyTo;
  short scriptInfoIRsiPeriod;
  AppliedPrice scriptInfoIRsiApplyTo;
  short scriptInfoIStochasticKPeriod;
  short scriptInfoIStochasticDPeriod;
  short scriptInfoIStochasticSlowing;
  SmoothingMethod scriptInfoIStochasticMethod;
  PriceField scriptInfoIStochasticPriceField;
}