package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Price field used for Stochastic Oscillator calculations. Corresponds to {@code ENUM_STO_PRICE} in MetaTrader 5.
 */
@NullMarked
@Getter
@AllArgsConstructor
public enum PriceField {

  /**
   * Calculation based on Low/High prices.
   */
  STO_LOWHIGH("STO_LOWHIGH"),

  /**
   * Calculation based on Close/Close prices.
   */
  STO_CLOSECLOSE("STO_CLOSECLOSE");

  /**
   * The MQL5 constant eaName string representation.
   */
  private final String value;
}
