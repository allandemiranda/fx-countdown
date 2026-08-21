package br.allandemiranda.fx.robot.dto.core.create;

import br.allandemiranda.fx.robot.model.core.Symbol;
import java.io.Serializable;
import java.math.BigDecimal;

public record SymbolCreateDto(
    String symbolName,
    BigDecimal point,
    BigDecimal swapLong,
    BigDecimal swapShort
) implements Serializable, Symbol {

}
