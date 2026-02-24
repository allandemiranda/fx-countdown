package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Dashboard}
 */
@Value
public class DashboardDto implements Serializable {

  @NotNull
  UUID id;
  @NotNull
  ChartDto chart;
  @NotNull
  DashboardStatus dashboardStatus;
  @NotNull
  DashboardDto.GarchInputsDto garchInputs;

  /**
   * DTO for {@link br.allandemiranda.fx.robot.model.embeddable.GarchInputs}
   */
  @Value
  public static class GarchInputsDto implements Serializable {

    @Positive
    int horizon;
    @Min(50)
    int priceSize;
    @Positive
    double kTP;
    @Positive
    double kSL;
  }
}