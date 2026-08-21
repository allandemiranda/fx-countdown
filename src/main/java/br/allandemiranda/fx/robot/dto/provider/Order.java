package br.allandemiranda.fx.robot.dto.provider;

import jakarta.validation.constraints.NotNull;

public interface Order extends PriceRiskLevel {

  @NotNull
  String comment();
}
