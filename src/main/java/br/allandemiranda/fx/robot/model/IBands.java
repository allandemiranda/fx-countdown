package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param period     period for average line calculation
 * @param shift      horizontal shift of the indicator
 * @param deviations number of standard deviations
 * @param applyTo    type of price or handle
 */
@Table("i_bands")
public record IBands(@Id @Column("chart_id") @NotNull UUID chartId, @Column("bands_period") @Positive short period, @Column("bands_shift") @PositiveOrZero short shift, @Column("deviation") @NotNull @PositiveOrZero BigDecimal deviations,
                     @Column("applied_price") @NotNull AppliedPrice applyTo) implements InputObjectModel {

}
