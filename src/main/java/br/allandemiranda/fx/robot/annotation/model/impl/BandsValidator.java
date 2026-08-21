package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.BandsValidate;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BandsValidator implements ConstraintValidator<BandsValidate, Bands> {

  @Override
  public boolean isValid(Bands bands, ConstraintValidatorContext context) {
    BigDecimal upper = bands.upperBand();
    BigDecimal base = bands.baseLine();
    BigDecimal lower = bands.lowerBand();

    return upper.compareTo(base) >= 0 && base.compareTo(lower) >= 0;
  }
}
