package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record DashboardDto(@NotNull ChartDto chartDto, @NotNull DashboardStatus status, @NotNull @PastOrPresent OffsetDateTime updateTime, @NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope) implements Serializable, BaseDto, InputObjectDto {

}