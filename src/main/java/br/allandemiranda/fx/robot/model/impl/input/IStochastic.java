package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.InputDashboardModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("i_stochastic")
public record IStochastic(@Id @Column("chart_id") @NotNull UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @Column("k_period") @Positive short kPeriod, @Column("d_period") @Positive short dPeriod,
                          @Column("slowing") @Positive short slowing, @Column("ma_method") SmoothingMethod method, @Column("price_field") PriceField priceField) implements InputDashboardModel {

}
