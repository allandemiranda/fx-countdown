package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.IADX;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IADXDto(
    UUID id,
    String eaName,
    short adxPeriod
) implements Serializable, Input, IADX {

}
