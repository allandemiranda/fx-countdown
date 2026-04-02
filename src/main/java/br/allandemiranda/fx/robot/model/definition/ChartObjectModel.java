package br.allandemiranda.fx.robot.model.definition;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface ChartObjectModel {

  UUID id();

  UUID chartId();

  OffsetDateTime timestamp();

}
