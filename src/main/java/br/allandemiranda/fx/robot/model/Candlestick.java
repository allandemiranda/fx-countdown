package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.annotation.CandlestickPriceValidate;
import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("candlestick")
@CandlestickPriceValidate
public record Candlestick(@Id @Column("id") @NotNull UUID id, @Column("symbol_name") @NotNull @Pattern(regexp = "^[A-Z]{6}$") String symbolName, @Column("timeframe") @NotNull Timeframe timeframe,
                          @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("open") @NotNull @Positive BigDecimal open, @Column("high") @NotNull @Positive BigDecimal high,
                          @Column("low") @NotNull @Positive BigDecimal low, @Column("close") @NotNull @Positive BigDecimal close) {

}
