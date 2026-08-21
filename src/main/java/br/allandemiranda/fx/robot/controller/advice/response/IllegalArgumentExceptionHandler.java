package br.allandemiranda.fx.robot.controller.advice.response;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice translating {@link IllegalArgumentException} into HTTP 400 Bad Request responses.
 */
@NullMarked
@Validated
@RestControllerAdvice
public class IllegalArgumentExceptionHandler {

  /**
   * Handles {@link IllegalArgumentException} and returns a standardized {@link CodeResponseHandler}.
   *
   * @param ex the caught IllegalArgumentException
   * @return the error response object
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public CodeResponseHandler handle(IllegalArgumentException ex) {
    return new CodeResponseHandler(ex.getClass().getSimpleName(), ex.getMessage());
  }

}
