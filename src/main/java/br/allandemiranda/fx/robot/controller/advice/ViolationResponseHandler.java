package br.allandemiranda.fx.robot.controller.advice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ViolationResponseHandler(@NotNull @NotEmpty String error, @NotNull @NotEmpty String message) {

}
