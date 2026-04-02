package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.NonNull;

public record IStochasticDto(@NotNull ChartDto chartDto, short kPeriod, short dPeriod, short slowing, @NonNull SmoothingMethod method, @NonNull PriceField priceField) implements Serializable, BaseDto, InputObjectDto {

}
