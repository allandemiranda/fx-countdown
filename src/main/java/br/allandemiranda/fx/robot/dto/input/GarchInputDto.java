package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.GarchInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record GarchInputDto(
    UUID id,
    String eaName,
    int horizon,
    int priceSize
) implements Serializable, Input, GarchInput {

}
