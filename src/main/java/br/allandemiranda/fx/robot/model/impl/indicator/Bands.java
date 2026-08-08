package br.allandemiranda.fx.robot.model.impl.indicator;

import br.allandemiranda.fx.robot.model.IndicatorModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("indicator_bands")
public record Bands(@Id @NotNull @Column("id") UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @NotNull @PastOrPresent @Column("timestamp") OffsetDateTime timestamp, @Column("base_line") @NotNull BigDecimal baseLine,
                    @Column("upper_band") @NotNull BigDecimal upperBand, @Column("lower_band") @NotNull BigDecimal lowerBand) implements IndicatorModel {

}
