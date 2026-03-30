package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("indicator_macd")
public record MACD(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("main_line") @NotNull BigDecimal mainLine, @Column("signal_line") @NotNull BigDecimal signalLine) {

}
