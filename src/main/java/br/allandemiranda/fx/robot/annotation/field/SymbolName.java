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
@Pattern(regexp = "^[A-Z]{6}$", message = "The symbol must contain exactly 6 capital letters (e.g., EURUSD)")
public @interface SymbolName {

  Class<?>[] groups() default {};

  String message() default "Invalid financial symbol";

  Class<? extends Payload>[] payload() default {};
}