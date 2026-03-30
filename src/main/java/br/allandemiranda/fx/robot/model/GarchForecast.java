package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param sigmaAgg sqrt(sum sigmaNext^2)
 */
@Table("garch_forecast")
public record GarchForecast(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("omega") double omega, @Column("alpha") double alpha, @Column("beta") double beta,
                            @Column("sigma_agg") double sigmaAgg) {

}
