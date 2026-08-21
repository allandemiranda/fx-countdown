package br.allandemiranda.fx.robot.controller.advice.response;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.exception.NotFoundException;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller advice translating {@link NotFoundException} into HTTP 404 Not Found responses.
 */
@NullMarked
@Validated
@RestControllerAdvice
public class NotFoundExceptionHandler {

  /**
   * Handles {@link NotFoundException} and returns a standardized {@link CodeResponseHandler}.
   *
   * @param <E> the specific exception subclass
   * @param ex  the caught NotFoundException
   * @return the error response object
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public <E extends NotFoundException> CodeResponseHandler handle(E ex) {
    return new CodeResponseHandler(ex.getClass().getSimpleName(), ex.getMessage());
  }

}
