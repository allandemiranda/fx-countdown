package br.allandemiranda.fx.robot.controller.advice;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Validated
@RestControllerAdvice
public class NotFoundExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public <E extends NotFoundException> CodeResponseHandler handle(@NonNull E ex) {
    return new CodeResponseHandler(ex.getClass().getSimpleName(), ex.getMessage());
  }

}
