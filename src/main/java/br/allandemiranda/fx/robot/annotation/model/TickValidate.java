package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.AskBidValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AskBidValidator.class)
@Documented
public @interface TickValidate {

  Class<?>[] groups() default {};

  String message() default "Ask must be greater than or equal to Bid tick price";

  Class<? extends Payload>[] payload() default {};
}
