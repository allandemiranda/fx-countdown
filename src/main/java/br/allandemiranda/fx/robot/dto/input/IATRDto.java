package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.IATR;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;

public record IATRDto(
    UUID id,
    String eaName,
    short maPeriod
) implements Serializable, Input, IATR {

}
