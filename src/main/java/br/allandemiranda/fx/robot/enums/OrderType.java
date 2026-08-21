package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Getter
@AllArgsConstructor
public enum OrderType {
  ORDER_TYPE_BUY("ORDER_TYPE_BUY"),
  ORDER_TYPE_SELL("ORDER_TYPE_SELL");

  private final String value;
}
