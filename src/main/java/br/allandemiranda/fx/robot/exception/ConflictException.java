package br.allandemiranda.fx.robot.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Base abstract exception thrown when a requested operation causes a state conflict (HTTP 409).
 */
@ResponseStatus(HttpStatus.CONFLICT)
@StandardException
public abstract class ConflictException extends RuntimeException {

}
