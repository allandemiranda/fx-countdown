package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record ChartDto(@NotNull UUID id, @NotNull SymbolDto symbol, @NotNull Timeframe period) implements Serializable, BaseDto {

}