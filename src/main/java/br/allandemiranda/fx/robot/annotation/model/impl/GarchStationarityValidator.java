package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.GarchStationarity;
import br.allandemiranda.fx.robot.dto.provider.GarchForecast;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class GarchStationarityValidator implements ConstraintValidator<GarchStationarity, GarchForecast> {

  @Override
  public boolean isValid(GarchForecast forecast, ConstraintValidatorContext context) {
    double alpha = forecast.alpha();
    double beta = forecast.beta();
    return (alpha + beta) < 1.0;
  }
}
