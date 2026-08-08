package br.allandemiranda.fx.robot.validation;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.model.Tick;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class AskBidValidator implements ConstraintValidator<AskBidValidate, Object> {

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    BigDecimal ask = null;
    BigDecimal bid = null;

    switch (value) {
      case Tick model -> {
        ask = model.ask();
        bid = model.bid();
      }
      case TickDto dto -> {
        ask = dto.ask();
        bid = dto.bid();
      }
      case TickCreateDto createDto -> {
        ask = createDto.ask();
        bid = createDto.bid();
      }
      default -> {
        return true;
      }
    }

    context.disableDefaultConstraintViolation();

    if (ask == null || bid == null) {
      if (ask == null) {
        context.buildConstraintViolationWithTemplate("ask must be not null").addPropertyNode("ask").addConstraintViolation();
      }
      if (bid == null) {
        context.buildConstraintViolationWithTemplate("bid must be not null").addPropertyNode("bid").addConstraintViolation();
      }

      return false;
    }

    if (ask.compareTo(bid) < 0) {
      context.buildConstraintViolationWithTemplate("ask must be greater than or equal to bid").addPropertyNode("ask").addConstraintViolation();
      return false;
    }

    return true;
  }
}