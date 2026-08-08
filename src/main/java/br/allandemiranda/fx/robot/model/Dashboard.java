package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("dashboard")
public record Dashboard(@Id @Column("id") @NotNull UUID id, @Column("symbol_name") @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @Column("timeframe") @NotNull Timeframe timeframe,
                        @Column("status") @NotNull DashboardStatus status, @Column("update_time") @NotNull @PastOrPresent LocalDateTime updateTime, @Column("scope_start_time") @NotNull @PastOrPresent OffsetDateTime startScope,
                        @Column("scope_end_time") @NotNull @PastOrPresent OffsetDateTime endScope, @Column("version") @PositiveOrZero int version,
                        @Column("minimal_level_accepted") @Positive @Max(100) BigDecimal minimalLevelAccepted) {

}
