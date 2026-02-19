package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Chart}
 */
@Value
public class ChartDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  SymbolDto symbol;
  Timeframe period;
  @NotNull
  ScriptInfoDto scriptInfo;
}