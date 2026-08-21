package br.allandemiranda.fx.robot.controller.advice.response;

import br.allandemiranda.fx.robot.controller.advice.ViolationResponseHandler;
import jakarta.validation.ConstraintViolationException;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice translating {@link ConstraintViolationException} into HTTP 400 Bad Request responses.
 */
@NullMarked
@Validated
@RestControllerAdvice
public class ViolationExceptionHandler {

  /**
   * Handles {@link ConstraintViolationException} and returns a formatted {@link ViolationResponseHandler}.
   *
   * @param ex the caught constraint violation exception
   * @return the validation violation response object
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ViolationResponseHandler handle(ConstraintViolationException ex) {
    String error = "Constraint violation";
    return new ViolationResponseHandler(error, ex.getMessage());
  }

}
