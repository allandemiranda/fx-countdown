package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.NonNull;

public record IStochasticCreateDto(short kPeriod, short dPeriod, short slowing, @NonNull SmoothingMethod method, @NonNull PriceField priceField) implements Serializable {

}
