package br.allandemiranda.fx.robot.model.analysis;

import jakarta.validation.constraints.NotEmpty;

public interface XgBoostModelData {

  @NotEmpty
  byte[] modelDataBuy();

  @NotEmpty
  byte[] modelDataSell();

}
