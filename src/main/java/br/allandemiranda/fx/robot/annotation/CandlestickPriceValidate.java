package br.allandemiranda.fx.robot.annotation;

import br.allandemiranda.fx.robot.validation.CandlestickValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CandlestickValidator.class)
@Documented
public @interface CandlestickPriceValidate {

  String message() default "Candlestick must contain the correct price";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
