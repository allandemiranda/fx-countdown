package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.PriceRiskLevelValidate;
import br.allandemiranda.fx.robot.dto.provider.PriceRiskLevel;
import br.allandemiranda.fx.robot.enums.OrderType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class PriceRiskLevelValidator implements ConstraintValidator<PriceRiskLevelValidate, PriceRiskLevel> {

  @Override
  public boolean isValid(PriceRiskLevel riskLevel, ConstraintValidatorContext context) {
    OrderType orderType = riskLevel.orderType();
    BigDecimal tp = riskLevel.tpPrice();
    BigDecimal sl = riskLevel.slPrice();

    int comparison = tp.compareTo(sl);

    if (orderType == OrderType.ORDER_TYPE_BUY) {
      if (comparison <= 0) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("For BUY orders, the TP price (" + tp + ") must be greater than the SL price (" + sl + ")").addConstraintViolation();
        return false;
      }
    } else if (orderType == OrderType.ORDER_TYPE_SELL && comparison >= 0) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("For SELL orders, the TP price (" + tp + ") must be lower than the SL price (" + sl + ")").addConstraintViolation();
      return false;
    }

    return true;
  }
}
