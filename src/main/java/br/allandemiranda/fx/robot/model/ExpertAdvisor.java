package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("expert_advisor")
public record ExpertAdvisor(@Id @Column("id") UUID id, @Column("name") @NotNull @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name, @Column("symbol_name") @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName,
                            @Column("timeframe") @NotNull Timeframe timeframe, @Column("update_time") @NotNull @PastOrPresent LocalDateTime updateTime,
                            @Column("status") @NotNull ExpertAdvisorStatus status, @Column("description") @NotNull String description) {

}
