package br.allandemiranda.fx.robot.validation;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
import br.allandemiranda.fx.robot.model.Tick;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AskBidValidator implements ConstraintValidator<AskBidValidate, Tick> {

  @Override
  public boolean isValid(Tick tick, ConstraintValidatorContext context) {

    boolean valid = tick.getAsk() >= tick.getBid();

    if (!valid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate("ask must be greater than or equal to bid")
          .addPropertyNode("ask")
          .addConstraintViolation();
    }

    return valid;
  }
}
