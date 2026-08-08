package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.dto.Timeseries;
import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.PositionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record TradingDto(@NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull PositionType positionType, @NotNull TickDto openTick, @NotNull TickDto closeTick, DealReason dealReason) implements Serializable, Timeseries {

}
