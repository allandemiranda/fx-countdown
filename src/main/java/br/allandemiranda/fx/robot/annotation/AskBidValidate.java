package br.allandemiranda.fx.robot.annotation;

import br.allandemiranda.fx.robot.validation.AskBidValidator;
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
public @interface AskBidValidate {

  String message() default "Ask must be greater than or equal to Bid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
