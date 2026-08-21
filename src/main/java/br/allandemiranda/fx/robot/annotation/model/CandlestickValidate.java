package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.CandlestickValidator;
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
public @interface CandlestickValidate {

  Class<?>[] groups() default {};

  String message() default "CandlestickValidate must contain the correct price structure";

  Class<? extends Payload>[] payload() default {};
}
