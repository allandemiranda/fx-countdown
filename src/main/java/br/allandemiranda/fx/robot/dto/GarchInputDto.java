package br.allandemiranda.fx.robot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.NonNull;

public record GarchInputDto(@NotNull ChartDto chartDto, @Positive int horizon, @Min(50) int priceSize, @NonNull @Positive BigDecimal kTP, @NonNull @Positive BigDecimal kSL) implements Serializable {

}
