package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AskBidValidate
@Table("tick")
public record Tick(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("ask") @NotNull @Positive BigDecimal ask, @Column("bid") @NotNull @Positive BigDecimal bid) {

}
