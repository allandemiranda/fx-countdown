package br.allandemiranda.fx.robot.dto.base;

import br.allandemiranda.fx.robot.dto.definition.BaseDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record GarchTradingDto(@NotNull UUID id, @NotNull ChartDto chartDto, @NotNull @PastOrPresent OffsetDateTime timestamp, @NotNull @Positive BigDecimal buyOpenPrice, @NotNull @Positive BigDecimal buyTpPrice, @NotNull @Positive BigDecimal buySlPrice, int buyPoints, @PastOrPresent OffsetDateTime closeBuyTime,
                              DealReason buyDealReason, @NotNull @Positive BigDecimal sellOpenPrice, @NotNull @Positive BigDecimal sellTpPrice, @NotNull @Positive BigDecimal sellSlPrice, int sellPoints, @PastOrPresent OffsetDateTime closeSellTime, DealReason sellDealReason) implements Serializable,
    BaseDto, ChartObjectDto {

}
