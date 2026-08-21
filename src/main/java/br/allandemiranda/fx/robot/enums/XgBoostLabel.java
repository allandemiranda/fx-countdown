package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Binary classification target label for XGBoost training rows.
 */
@Getter
@AllArgsConstructor
public enum XgBoostLabel {

  /**
   * Positive outcome (Class 1): The simulated trade hit Take Profit (TP).
   */
  OPEN(1),

  /**
   * Negative outcome (Class 0): The simulated trade hit Stop Loss (SL) or negative swap rollover.
   */
  NOT_OPEN(0);

  /**
   * Numerical integer label passed to the XGBoost DMatrix (0 or 1).
   */
  private final int value;
}
