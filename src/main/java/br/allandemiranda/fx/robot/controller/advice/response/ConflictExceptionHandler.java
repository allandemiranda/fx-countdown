package br.allandemiranda.fx.robot.controller.advice.response;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.exception.ConflictException;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice translating {@link ConflictException} into HTTP 409 Conflict responses.
 */
@NullMarked
@Validated
@RestControllerAdvice
public class ConflictExceptionHandler {

  /**
   * Handles {@link ConflictException} and returns a standardized {@link CodeResponseHandler}.
   *
   * @param <E> the specific exception subclass
   * @param ex  the caught ConflictException
   * @return the error response object
   */
  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public <E extends ConflictException> CodeResponseHandler handle(E ex) {
    return new CodeResponseHandler(ex.getClass().getSimpleName(), ex.getMessage());
  }

}
