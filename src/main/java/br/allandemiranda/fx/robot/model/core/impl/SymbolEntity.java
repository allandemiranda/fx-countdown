package br.allandemiranda.fx.robot.model.core.impl;

import br.allandemiranda.fx.robot.model.core.Symbol;
import java.io.Serializable;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("core_symbol")
public record SymbolEntity(
    @Id @Column("eaName") String symbolName,
    @Column("point") BigDecimal point,
    @Column("swap_long") BigDecimal swapLong,
    @Column("swap_short") BigDecimal swapShort
) implements Serializable, Symbol {

}
