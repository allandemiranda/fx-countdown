package br.allandemiranda.fx.robot.controller.advice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Error response payload structure for bean constraint validation violations.
 *
 * @param error   error classification label
 * @param message constraint violation details
 */
public record ViolationResponseHandler(
    @NotNull @NotEmpty String error,
    @NotNull @NotEmpty String message
) {

}
