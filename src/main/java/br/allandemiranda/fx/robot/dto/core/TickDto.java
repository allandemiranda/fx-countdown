package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.model.core.SymbolId;
import br.allandemiranda.fx.robot.model.core.Tick;
import br.allandemiranda.fx.robot.model.core.TickId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TickDto(
    UUID id,
    String symbolName,
    OffsetDateTime timestamp,
    BigDecimal ask,
    BigDecimal bid
) implements Serializable, SymbolId, TickId, Tick {

}
