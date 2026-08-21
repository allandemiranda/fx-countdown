package br.allandemiranda.fx.robot.dto.core.create;

import br.allandemiranda.fx.robot.model.core.Tick;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TickCreateDto(
    OffsetDateTime timestamp,
    BigDecimal ask,
    BigDecimal bid
) implements Serializable, Tick {

}
