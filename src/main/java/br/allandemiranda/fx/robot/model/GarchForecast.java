package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param sigmaAgg sqrt(sum sigmaNext^2)
 */
@Table("garch_forecast")
public record GarchForecast(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("omega") @NotNull BigDecimal omega, @Column("alpha") @NotNull BigDecimal alpha,
                            @Column("beta") @NotNull BigDecimal beta,
                            @Column("sigma_agg") @NotNull BigDecimal sigmaAgg) implements ChartObjectModel {

}
