package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.DashboardStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("dashboard")
public record Dashboard(@Id @Column("chart_id") @NotNull UUID chartId, @Column("status") @NotNull DashboardStatus status, @Column("update_time") @NotNull @PastOrPresent OffsetDateTime updateTime, @Column("scope_start_time") @NotNull @PastOrPresent OffsetDateTime startScope,
                        @Column("scope_end_time") @NotNull @PastOrPresent OffsetDateTime endScope) {

}
