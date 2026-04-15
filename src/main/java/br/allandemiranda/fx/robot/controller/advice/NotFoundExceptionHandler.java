package br.allandemiranda.fx.robot.controller.advice;

import br.allandemiranda.fx.robot.exception.NotFoundException;
import java.util.Map;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public <E extends NotFoundException> Map<String, Object> handle(@NonNull E ex) {
    return Map.of(
        "type", ex.getClass().getSimpleName(),
        "message", ex.getMessage()
    );
  }
}
