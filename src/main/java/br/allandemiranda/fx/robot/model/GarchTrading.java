package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.model.type.ChartObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Column;

@Data
@EqualsAndHashCode(callSuper = true)
public class GarchTrading extends ChartObject {

  // ----- BUY

  @NotNull
  @Positive
  @Column("buy_open_price")
  private BigDecimal buyOpenPrice;

  @NotNull
  @Positive
  @Column("buy_tp_price")
  private BigDecimal buyTpPrice;

  @NotNull
  @Positive
  @Column("buy_sl_price")
  private BigDecimal buySlPrice;

  @Column("buy_result_points")
  private int buyPoints; // Result of buy operation in points (including fees and rollover)

  @PastOrPresent
  @Column("buy_close_time")
  private OffsetDateTime closeBuyTime;

  @Column("buy_deal_reason")
  private DealReason buyDealReason;

  // ----- SELL

  @NotNull
  @Positive
  @Column("sell_open_price")
  private BigDecimal sellOpenPrice;

  @NotNull
  @Positive
  @Column("sell_tp_price")
  private BigDecimal sellTpPrice;

  @NotNull
  @Positive
  @Column("sell_sl_price")
  private BigDecimal sellSlPrice;

  @Column("sell_result_points")
  private int sellPoints; // Result of sell operation in points (including fees and rollover)

  @PastOrPresent
  @Column("sell_close_time")
  private OffsetDateTime closeSellTime;

  @Column("sell_deal_reason")
  private DealReason sellDealReason;

}
