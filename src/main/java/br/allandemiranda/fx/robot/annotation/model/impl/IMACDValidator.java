package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.IMACDValidate;
import br.allandemiranda.fx.robot.model.input.IMACD;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class IMACDValidator implements ConstraintValidator<IMACDValidate, IMACD> {

  @Override
  public boolean isValid(IMACD params, ConstraintValidatorContext context) {
    return params.fastEmaPeriod() < params.slowEmaPeriod();
  }
}
