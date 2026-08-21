package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.ScopeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScopeValidator.class)
@Documented
public @interface ScopeValidate {

  Class<?>[] groups() default {};

  String message() default "Start scope time must be before end scope time";

  Class<? extends Payload>[] payload() default {};
}
