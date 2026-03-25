package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("chart")
public class Chart {

  @Id
  @NotNull
  @Column("id")
  private UUID id;

  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @Column("symbol_name")
  private String symbolName;

  @NotNull
  @Column("period")
  private Timeframe period;

}
