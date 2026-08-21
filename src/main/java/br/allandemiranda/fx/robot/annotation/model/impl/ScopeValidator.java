package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.ScopeValidate;
import br.allandemiranda.fx.robot.model.input.ScopeInput;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class ScopeValidator implements ConstraintValidator<ScopeValidate, ScopeInput> {

  @Override
  public boolean isValid(ScopeInput value, ConstraintValidatorContext context) {
    return value.startScope().isBefore(value.endScope());
  }
}
