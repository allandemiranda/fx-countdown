package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.dto.Timeseries;
import br.allandemiranda.fx.robot.enums.PositionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record PriceRiskLevelDto(@NotNull @PastOrPresent OffsetDateTime timestamp,
                                @NotNull PositionType positionType,
                                @NotNull @Positive BigDecimal tpPrice,
                                @NotNull @Positive BigDecimal slPrice
) implements Serializable, Timeseries {

}
