package br.allandemiranda.fx.robot.model.core.impl;

import br.allandemiranda.fx.robot.model.core.SymbolId;
import br.allandemiranda.fx.robot.model.core.Tick;
import br.allandemiranda.fx.robot.model.core.TickId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("core_tick")
public record TickEntity(
    @Id @Column("id") UUID id,
    @Column("symbol_name") String symbolName,
    @Column("timestamp") OffsetDateTime timestamp,
    @Column("ask") BigDecimal ask,
    @Column("bid") BigDecimal bid
) implements Serializable, SymbolId, TickId, Tick {

}
