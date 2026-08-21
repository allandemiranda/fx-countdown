package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.IADX;
import java.io.Serializable;

public record IADXCreateDto(
    short adxPeriod
) implements Serializable, InputCreate, IADX {

}
