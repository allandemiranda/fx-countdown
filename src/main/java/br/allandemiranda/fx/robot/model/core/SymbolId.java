package br.allandemiranda.fx.robot.model.core;

import br.allandemiranda.fx.robot.annotation.field.SymbolName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public interface SymbolId {

  @NotNull
  @SymbolName
  @NotEmpty
  @NotBlank
  String symbolName();

}
