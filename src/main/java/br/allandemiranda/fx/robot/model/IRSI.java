package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param period  averaging period
 * @param applyTo type of price or handle
 */
@Table("i_rsi")
public record IRSI(@Id @Column("chart_id") @NotNull UUID chartId, @Column("ma_period") @Positive short period, @Column("applied_price") AppliedPrice applyTo) implements InputObjectModel {

}
