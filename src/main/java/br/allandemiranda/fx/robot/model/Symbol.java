package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("symbol")
public class Symbol {

  @Id
  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @Column("name")
  private String name;

  @NotNull
  @Positive
  @Column("point")
  private BigDecimal point;

  @NotNull
  @Column("swap_long")
  private BigDecimal swapLong;

  @NotNull
  @Column("swap_short")
  private BigDecimal swapShort;

}