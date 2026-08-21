package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.GarchInput;
import java.io.Serializable;

public record GarchInputCreateDto(
    int horizon,
    int priceSize
) implements Serializable, InputCreate, GarchInput {

}
