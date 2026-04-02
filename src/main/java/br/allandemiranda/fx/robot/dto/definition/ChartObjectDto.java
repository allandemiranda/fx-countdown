package br.allandemiranda.fx.robot.dto.definition;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import java.time.OffsetDateTime;
import java.util.UUID;

public interface ChartObjectDto {

  UUID id();

  ChartDto chartDto();

  OffsetDateTime timestamp();

}
