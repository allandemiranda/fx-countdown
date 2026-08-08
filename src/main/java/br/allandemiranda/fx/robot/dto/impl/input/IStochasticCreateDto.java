package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import java.io.Serializable;
import lombok.NonNull;

public record IStochasticCreateDto(short kPeriod, short dPeriod, short slowing, @NonNull SmoothingMethod method, @NonNull PriceField priceField) implements Serializable, InputDashboardCreateDto {

}
