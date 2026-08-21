package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Enumeration of trade closure reasons corresponding to MQL5 {@code ENUM_DEAL_REASON}. Indicates how a simulated trade position exited.
 */
@NullMarked
@Getter
@AllArgsConstructor
public enum DealReason {

  /**
   * Position was closed upon reaching the Stop Loss threshold.
   */
  DEAL_REASON_SL("DEAL_REASON_SL"),

  /**
   * Position was closed upon reaching the Take Profit threshold.
   */
  DEAL_REASON_TP("DEAL_REASON_TP"),

  /**
   * Position was closed due to accumulated negative rollover/swap costs exceeding expected profit.
   */
  DEAL_REASON_ROLLOVER("DEAL_REASON_ROLLOVER");

  /**
   * The MQL5 identifier text value.
   */
  private final String value;
}
