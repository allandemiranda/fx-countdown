package br.allandemiranda.fx.robot.dto.operation;

import br.allandemiranda.fx.robot.enums.OrderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;

public record OrderDto(@NotNull @Pattern(regexp = "^[A-Z]{6}$") @NotEmpty @NotBlank String symbol, @NotNull OrderType type, @Positive BigDecimal tp, @Positive BigDecimal sl, @NotNull String comment) implements Serializable {

}
