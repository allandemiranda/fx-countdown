package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.CandlestickValidate;
import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class CandlestickValidator implements ConstraintValidator<CandlestickValidate, Candlestick> {

  private static void addViolation(ConstraintValidatorContext context, String message, String property) {
    context.buildConstraintViolationWithTemplate(message).addPropertyNode(property).addConstraintViolation();
  }

  @Override
  public boolean isValid(Candlestick value, ConstraintValidatorContext context) {
    BigDecimal open = value.open();
    BigDecimal high = value.high();
    BigDecimal low = value.low();
    BigDecimal close = value.close();

    boolean isValid = true;
    context.disableDefaultConstraintViolation();

    if (high.compareTo(low) < 0) {
      CandlestickValidator.addViolation(context, "High price must be greater than or equal to low price", "high");
      isValid = false;
    }

    if (high.compareTo(open) < 0) {
      CandlestickValidator.addViolation(context, "High price must be greater than or equal to open price", "high");
      isValid = false;
    }

    if (high.compareTo(close) < 0) {
      CandlestickValidator.addViolation(context, "High price must be greater than or equal to close price", "high");
      isValid = false;
    }

    if (low.compareTo(open) > 0) {
      CandlestickValidator.addViolation(context, "Low price must be lower than or equal to open price", "low");
      isValid = false;
    }

    if (low.compareTo(close) > 0) {
      CandlestickValidator.addViolation(context, "Low price must be lower than or equal to close price", "low");
      isValid = false;
    }

    return isValid;
  }
}