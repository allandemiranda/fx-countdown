package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("symbol")
public record Symbol(@Id @Column("name") @NotNull @Size(min = 6, max = 6) @Pattern(regexp = "^[A-Z]{6}$") String name, @Column("point") @NotNull @Positive BigDecimal point, @Column("swap_long") @NotNull BigDecimal swapLong, @Column("swap_short") @NotNull BigDecimal swapShort) {

}