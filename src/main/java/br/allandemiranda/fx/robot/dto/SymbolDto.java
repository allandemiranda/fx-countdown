package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Value;

/**
 * DTO for {@link br.allandemiranda.fx.robot.model.Symbol}
 */
@Value
public class SymbolDto implements Serializable {

  @NotNull
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @NotEmpty
  @NotBlank
  String name;
  @NotNull
  @Positive
  BigDecimal point;
  @NotNull
  BigDecimal swapLong;
  @NotNull
  BigDecimal swapShort;
}