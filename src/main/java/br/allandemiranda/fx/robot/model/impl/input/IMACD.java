package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.InputDashboardModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_macd")
public record IMACD(@Id @Column("chart_id") @NotNull UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @Column("fast_ema_period") @Positive short fastEma, @Column("slow_ema_period") @Positive short slowEma,
                    @Column("signal_period") @Positive short macdSma, @Column("applied_price") @NotNull AppliedPrice applyTo) implements InputDashboardModel {

}
