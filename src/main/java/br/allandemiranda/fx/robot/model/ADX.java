package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("indicator_adx")
public record ADX(@Id @NotNull @Column("id") UUID id, @NotNull @Column("chart_id") UUID chartId, @NotNull @PastOrPresent @Column("timestamp") OffsetDateTime timestamp, @NotNull @Column("main_line") BigDecimal mainLine, @NotNull @Column("plus_di_line") BigDecimal plusDiLine,
                  @NotNull @Column("mins_di_line") BigDecimal minusDiLine) {

}
