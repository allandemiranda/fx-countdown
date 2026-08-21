package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.annotation.model.PriceRiskLevelValidate;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

@PriceRiskLevelValidate
public interface PriceRiskLevel extends Timeseries {

  @NotNull
  OrderType orderType();

  @NotNull
  @Positive
  BigDecimal slPrice();

  @NotNull
  @Positive
  BigDecimal tpPrice();
}
