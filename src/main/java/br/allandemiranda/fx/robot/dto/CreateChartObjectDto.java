package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

public interface CreateChartObjectDto extends CreateDto {

  @NotNull
  @PastOrPresent
  OffsetDateTime timestamp();
}
