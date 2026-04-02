package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @param buyPoints  Result of buy operation in points (including fees and rollover)
 * @param sellPoints Result of sell operation in points (including fees and rollover)
 */
@Table("garch_trading")
public record GarchTrading(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp, @Column("buy_open_price") @NotNull @Positive BigDecimal buyOpenPrice,
                           @Column("buy_tp_price") @NotNull @Positive BigDecimal buyTpPrice, @Column("buy_sl_price") @NotNull @Positive BigDecimal buySlPrice, @Column("buy_result_points") int buyPoints, @Column("buy_close_time") @PastOrPresent OffsetDateTime closeBuyTime,
                           @Column("buy_deal_reason") DealReason buyDealReason, @Column("sell_open_price") @NotNull @Positive BigDecimal sellOpenPrice, @Column("sell_tp_price") @NotNull @Positive BigDecimal sellTpPrice, @Column("sell_sl_price") @NotNull @Positive BigDecimal sellSlPrice,
                           @Column("sell_result_points") int sellPoints, @Column("sell_close_time") @PastOrPresent OffsetDateTime closeSellTime, @Column("sell_deal_reason") DealReason sellDealReason) implements ChartObjectModel {

}
