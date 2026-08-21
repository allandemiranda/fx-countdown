package br.allandemiranda.fx.robot.controller.advice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Error response payload structure for controller exception advice.
 *
 * @param type    the exception simple class eaName or type
 * @param message detailed error description message
 */
public record CodeResponseHandler(
    @NotNull @NotEmpty String type,
    @NotNull @NotEmpty String message
) {

}
