package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.TickValidate;
import br.allandemiranda.fx.robot.model.core.Tick;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class AskBidValidator implements ConstraintValidator<TickValidate, Tick> {

  @Override
  public boolean isValid(Tick value, ConstraintValidatorContext context) {
    BigDecimal ask = value.ask();
    BigDecimal bid = value.bid();

    if (ask.compareTo(bid) < 0) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("Ask must be greater than or equal to bid").addPropertyNode("ask").addConstraintViolation();
      return false;
    }

    return true;
  }
}
