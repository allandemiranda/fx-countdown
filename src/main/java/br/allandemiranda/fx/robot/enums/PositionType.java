package br.allandemiranda.fx.robot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Getter
@AllArgsConstructor
public enum PositionType {
  POSITION_TYPE_BUY("POSITION_TYPE_BUY"),
  POSITION_TYPE_SELL("POSITION_TYPE_SELL");

  private final String value;

}
