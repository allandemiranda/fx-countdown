package br.allandemiranda.fx.robot.dto.impl.create;

import br.allandemiranda.fx.robot.dto.CreateChartObjectDto;
import br.allandemiranda.fx.robot.dto.impl.base.TickDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record GarchTradingCreateDto(@NotNull @PastOrPresent OffsetDateTime timestamp,
                                    @NotNull TickDto tickOpen,

                                    @NotNull @Positive BigDecimal buyTpPrice,
                                    @NotNull @Positive BigDecimal buySlPrice,

                                    @NotNull @Positive BigDecimal sellTpPrice,
                                    @NotNull @Positive BigDecimal sellSlPrice
) implements Serializable, CreateChartObjectDto {

}
