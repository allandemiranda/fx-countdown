package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.GarchStationarityValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = GarchStationarityValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GarchStationarity {

  Class<?>[] groups() default {};

  String message() default "The GARCH(1,1) model must be stationary: alpha + beta must be less than 1.0";

  Class<? extends Payload>[] payload() default {};
}
