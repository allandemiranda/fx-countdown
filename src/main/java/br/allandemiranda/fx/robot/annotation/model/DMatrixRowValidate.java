package br.allandemiranda.fx.robot.annotation.model;

import br.allandemiranda.fx.robot.annotation.model.impl.DMatrixTimeseriesSyncValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = DMatrixTimeseriesSyncValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DMatrixRowValidate {

  Class<?>[] groups() default {};

  String message() default "All time series must have the same size and perfectly synchronized timestamps in the same order.";

  Class<? extends Payload>[] payload() default {};
}
