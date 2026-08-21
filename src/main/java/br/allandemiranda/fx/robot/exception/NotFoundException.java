package br.allandemiranda.fx.robot.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base abstract exception thrown when a requested resource is not found (HTTP 404).
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@StandardException
public abstract class NotFoundException extends RuntimeException {

}
