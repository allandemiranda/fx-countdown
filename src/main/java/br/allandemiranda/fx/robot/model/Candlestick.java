package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("candlestick")
public record Candlestick(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("open") @NotNull @Positive BigDecimal open, @Column("high") @NotNull @Positive BigDecimal high,
                          @Column("low") @NotNull @Positive BigDecimal low, @Column("close") @NotNull @Positive BigDecimal close) implements ChartObjectModel {

}
