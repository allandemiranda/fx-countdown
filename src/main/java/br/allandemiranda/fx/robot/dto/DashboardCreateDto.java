package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record DashboardCreateDto(@NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope) implements Serializable {

}