package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("chart")
public record Chart(@Id @Column("id") @NotNull UUID id, @Column("symbol_name") @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @Column("period") @NotNull Timeframe period) {

}
