package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param period  averaging period
 * @param shift   horizontal shift
 * @param method  smoothing type
 * @param applyTo type of price or handle
 */
@Table("i_ma_fast")
public record IMAFast(@Id @Column("chart_id") @NotNull UUID chartId, @Column("ma_period") @Positive short period, @Column("ma_shift") @PositiveOrZero short shift, @Column("ma_method") @NotNull SmoothingMethod method, @Column("applied_price") @NotNull AppliedPrice applyTo) {

}
