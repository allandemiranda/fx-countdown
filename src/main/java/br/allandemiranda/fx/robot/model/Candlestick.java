package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("candlestick")
public record Candlestick(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("open") @NotNull @Positive double open, @Column("high") @NotNull @Positive double high,
                          @Column("low") @NotNull @Positive double low, @Column("close") @NotNull @Positive double close) {

}
