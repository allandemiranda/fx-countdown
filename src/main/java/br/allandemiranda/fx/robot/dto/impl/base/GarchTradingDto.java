package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.ChartObjectDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record GarchTradingDto(@NotNull UUID id,
                              @Valid @NotNull ChartDto chartDto,
                              @NotNull @PastOrPresent OffsetDateTime timestamp,

                              @NotNull @PastOrPresent OffsetDateTime openTime,

                              @NotNull @Positive BigDecimal buyOpenPrice,
                              @NotNull @Positive BigDecimal buyTpPrice,
                              @NotNull @Positive BigDecimal buySlPrice,
                              @NotNull BigDecimal buyPoints,
                              @NotNull @Positive BigDecimal closeBuyPrice,
                              @PastOrPresent OffsetDateTime closeBuyTime,
                              DealReason buyDealReason,

                              @NotNull @Positive BigDecimal sellOpenPrice,
                              @NotNull @Positive BigDecimal sellTpPrice,
                              @NotNull @Positive BigDecimal sellSlPrice,
                              @NotNull BigDecimal sellPoints,
                              @NotNull @Positive BigDecimal closeSellPrice,
                              @PastOrPresent OffsetDateTime closeSellTime,
                              DealReason sellDealReason
) implements Serializable, BaseDto, ChartObjectDto {

}
