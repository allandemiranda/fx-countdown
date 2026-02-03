package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record DashboardDto(@Valid @NotNull ChartDto chartDto, @NotNull DashboardStatus status, @NotNull @PastOrPresent OffsetDateTime updateTime, @NotNull @PastOrPresent OffsetDateTime startScope, @NotNull @PastOrPresent OffsetDateTime endScope, String fileName) implements Serializable, BaseDto,
    InputObjectDto {

}