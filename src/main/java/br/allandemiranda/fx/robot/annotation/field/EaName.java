package br.allandemiranda.fx.robot.annotation.field;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$", message = "The name must be between 1 and 20 characters long (letters, numbers, hyphens, or underscores)")
public @interface EaName {

  Class<?>[] groups() default {};

  String message() default "Invalid name";

  Class<? extends Payload>[] payload() default {};
}