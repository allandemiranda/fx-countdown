package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.model.core.Symbol;
import java.io.Serializable;
import java.math.BigDecimal;

public record SymbolDto(
    String symbolName,
    BigDecimal point,
    BigDecimal swapLong,
    BigDecimal swapShort
) implements Serializable, Symbol {

}