package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.model.type.ChartIdentifier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class Dashboard extends ChartIdentifier {

  @NotNull
  @Column("status")
  private DashboardStatus dashboardStatus = DashboardStatus.CREATED;

  @NotNull
  @PastOrPresent
  @Column("update_time")
  private OffsetDateTime updateTime;

  @NotNull
  @PastOrPresent
  @Column("scope_start_time")
  private OffsetDateTime startScope;

  @NotNull
  @PastOrPresent
  @Column("scope_end_time")
  private OffsetDateTime endScope;

  @NotNull
  @NotEmpty
  @NotBlank
  @Column("ml_file_name")
  private String mlFileName = "unknown";

}
