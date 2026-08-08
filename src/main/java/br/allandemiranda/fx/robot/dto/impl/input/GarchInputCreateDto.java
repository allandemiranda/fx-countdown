package br.allandemiranda.fx.robot.dto.impl.input;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;

public record GarchInputCreateDto(@Positive int horizon, @Min(50) int priceSize) implements Serializable, InputCreateDto {

}
