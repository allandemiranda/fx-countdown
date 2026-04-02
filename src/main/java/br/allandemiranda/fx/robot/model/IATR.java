package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param period averaging period
 */
@Table("i_atr")
public record IATR(@Id @Column("chart_id") @NotNull UUID chartId, @Column("ma_period") @Positive short period) implements InputObjectModel {

}
