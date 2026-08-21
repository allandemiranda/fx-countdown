package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

/**
 * Moving Average calculation method. Corresponds to {@code ENUM_MA_METHOD} in MetaTrader 5.
 */
@NullMarked
@Getter
@AllArgsConstructor
public enum SmoothingMethod {

  /**
   * Simple Moving Average (SMA).
   */
  MODE_SMA("MODE_SMA"),

  /**
   * Exponential Moving Average (EMA).
   */
  MODE_EMA("MODE_EMA"),

  /**
   * Smoothed Moving Average (SMMA).
   */
  MODE_SMMA("MODE_SMMA"),

  /**
   * Linear Weighted Moving Average (LWMA).
   */
  MODE_LWMA("MODE_LWMA");

  /**
   * The MQL5 constant eaName string representation.
   */
  private final String value;
}
