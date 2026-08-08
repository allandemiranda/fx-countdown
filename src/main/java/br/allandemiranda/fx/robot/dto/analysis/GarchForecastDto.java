package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.dto.Timeseries;
import br.allandemiranda.fx.robot.enums.PositionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record GarchForecastDto(@NotNull @PastOrPresent OffsetDateTime timestamp,
                               @NotNull @Positive BigDecimal price,
                               @NotNull PositionType positionType,
                               double omega,
                               double alpha,
                               double beta,
                               double sigmaAgg // sqrt(sum sigmaNext^2)
) implements Serializable, Timeseries {

}
