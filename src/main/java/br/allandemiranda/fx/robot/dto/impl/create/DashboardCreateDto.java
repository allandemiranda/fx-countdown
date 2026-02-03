package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateDto;
import br.allandemiranda.fx.robot.dto.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record DashboardCreateDto(@NotNull DashboardStatus status, @NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope, String fileName) implements Serializable, CreateDto, CreateInputObjectDto {

}