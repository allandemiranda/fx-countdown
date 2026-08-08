package br.allandemiranda.fx.robot.controller.advice.response;

import br.allandemiranda.fx.robot.controller.advice.CodeResponseHandler;
import br.allandemiranda.fx.robot.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Validated
@RestControllerAdvice
public class ConflictExceptionHandler {

  @ExceptionHandler(ConflictException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public <E extends ConflictException> CodeResponseHandler handle(E ex) {
    return new CodeResponseHandler(ex.getClass().getSimpleName(), ex.getMessage());
  }

}
