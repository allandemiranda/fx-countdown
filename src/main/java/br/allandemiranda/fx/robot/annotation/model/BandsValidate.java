package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.BandsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = BandsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BandsValidate {

  Class<?>[] groups() default {};

  String message() default "The bands must satisfy the condition: upperBand >= baseLine >= lowerBand";

  Class<? extends Payload>[] payload() default {};
}
