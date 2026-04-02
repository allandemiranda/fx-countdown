package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record GarchTradingCreateDto(@NotNull OffsetDateTime timestamp, @NotNull @Positive BigDecimal buyOpenPrice, @NotNull @Positive BigDecimal buyTpPrice, @NotNull @Positive BigDecimal buySlPrice, @NotNull @Positive BigDecimal sellOpenPrice, @NotNull @Positive BigDecimal sellTpPrice,
                                       @NotNull @Positive BigDecimal sellSlPrice) implements Serializable, CreateChartObjectDto {

}
