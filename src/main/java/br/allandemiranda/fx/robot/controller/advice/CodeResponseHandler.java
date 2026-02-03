package br.allandemiranda.fx.robot.controller.advice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CodeResponseHandler(@NotNull @NotEmpty String type, @NotNull @NotEmpty String message) {

}
