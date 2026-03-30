package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param kPeriod    K-period (number of bars for calculations)
 * @param dPeriod    D-period (period of first smoothing)
 * @param slowing    final smoothing
 * @param method     type of smoothing
 * @param priceField stochastic calculation method
 */
@Table("i_stochastic")
public record IStochastic(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("k_period") @Positive short kPeriod, @Column("d_period") @Positive short dPeriod, @Column("slowing") @Positive short slowing, @Column("ma_method") SmoothingMethod method,
                          @Column("price_field") PriceField priceField) {

}
