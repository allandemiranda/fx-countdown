package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

public record IStochasticCreateDto(short kPeriod, short dPeriod, short slowing, @NotNull SmoothingMethod method, @NotNull PriceField priceField) implements Serializable, InputDashboardCreateDto {

}
