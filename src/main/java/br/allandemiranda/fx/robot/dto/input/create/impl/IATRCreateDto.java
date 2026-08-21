package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.IATR;
import java.io.Serializable;

public record IATRCreateDto(
    short maPeriod
) implements Serializable, InputCreate, IATR {

}
