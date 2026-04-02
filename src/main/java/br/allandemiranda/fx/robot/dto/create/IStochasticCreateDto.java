package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import java.io.Serializable;
import lombok.NonNull;

public record IStochasticCreateDto(short kPeriod, short dPeriod, short slowing, @NonNull SmoothingMethod method, @NonNull PriceField priceField) implements Serializable, CreateInputObjectDto {

}
