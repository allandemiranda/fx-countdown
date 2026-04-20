package br.allandemiranda.fx.robot.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebInputException;

@Validated
@RestControllerAdvice
public class ViolationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ViolationResponseHandler handle(@NonNull ConstraintViolationException ex) {
    String error = "Constraint violation";
    return new ViolationResponseHandler(error, ex.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ServerWebInputException.class)
  public ViolationResponseHandler handle(@NonNull ServerWebInputException ex) {
    String error = "Invalid request parameter";
    return new ViolationResponseHandler(error, ex.getMessage());
  }
}
