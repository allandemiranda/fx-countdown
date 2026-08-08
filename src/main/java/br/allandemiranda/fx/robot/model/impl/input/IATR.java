package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputDashboardModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_atr")
public record IATR(@Id @Column("chart_id") @NotNull UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @Column("ma_period") @Positive short period) implements InputDashboardModel {

}
