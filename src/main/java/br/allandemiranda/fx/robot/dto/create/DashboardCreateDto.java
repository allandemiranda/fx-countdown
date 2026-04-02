package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record DashboardCreateDto(@NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope) implements Serializable, CreateDto {

}