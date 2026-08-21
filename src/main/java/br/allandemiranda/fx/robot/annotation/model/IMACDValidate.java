package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.IMACDValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = IMACDValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IMACDValidate {

  Class<?>[] groups() default {};

  String message() default "The period of the fast average (fastEmaPeriod) must be shorter than the period of the slow average (slowEmaPeriod).";

  Class<? extends Payload>[] payload() default {};
}
