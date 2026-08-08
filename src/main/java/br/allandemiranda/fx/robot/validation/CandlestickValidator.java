package br.allandemiranda.fx.robot.validation;

import br.allandemiranda.fx.robot.annotation.CandlestickPriceValidate;
import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.model.Candlestick;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class CandlestickValidator implements ConstraintValidator<CandlestickPriceValidate, Object> {

  private static void addViolation(ConstraintValidatorContext context, String message, String property) {
    context.buildConstraintViolationWithTemplate(message).addPropertyNode(property).addConstraintViolation();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    BigDecimal open;
    BigDecimal high;
    BigDecimal low;
    BigDecimal close;

    switch (value) {
      case Candlestick model -> {
        open = model.open();
        high = model.high();
        low = model.low();
        close = model.close();
      }
      case CandlestickDto dto -> {
        open = dto.open();
        high = dto.high();
        low = dto.low();
        close = dto.close();
      }
      case CandlestickCreateDto createDto -> {
        open = createDto.open();
        high = createDto.high();
        low = createDto.low();
        close = createDto.close();
      }
      default -> {
        return true;
      }
    }

    if (open == null || high == null || low == null || close == null) {
      if (open == null) {
        CandlestickValidator.addViolation(context, "open prices must be not null", "open");
      }
      if (high == null) {
        CandlestickValidator.addViolation(context, "high prices must be not null", "high");
      }
      if (low == null) {
        CandlestickValidator.addViolation(context, "low prices must be not null", "low");
      }
      if (close == null) {
        CandlestickValidator.addViolation(context, "close prices must be not null", "close");
      }

      return false;
    }

    boolean isValid = true;
    context.disableDefaultConstraintViolation();

    if (high.compareTo(low) < 0) {
      CandlestickValidator.addViolation(context, "high price must be greater than or equal to low price", "high");
      isValid = false;
    }

    if (high.compareTo(open) < 0) {
      CandlestickValidator.addViolation(context, "high price must be greater than or equal to open price", "high");
      isValid = false;
    }

    if (high.compareTo(close) < 0) {
      CandlestickValidator.addViolation(context, "high price must be greater than or equal to close price", "high");
      isValid = false;
    }

    if (low.compareTo(open) > 0) {
      CandlestickValidator.addViolation(context, "low price must be lower than or equal to open price", "low");
      isValid = false;
    }

    if (low.compareTo(close) > 0) {
      CandlestickValidator.addViolation(context, "low price must be lower than or equal to close price", "low");
      isValid = false;
    }

    return isValid;
  }
}