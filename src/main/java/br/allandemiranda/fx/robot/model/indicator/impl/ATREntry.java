package br.allandemiranda.fx.robot.model.indicator.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("indicator_atr")
public record ATREntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("symbol_name") String symbolName,
    @Column("timeframe") Timeframe timeframe,
    @Column("timestamp") OffsetDateTime timestamp,
    @Column("atrs") BigDecimal atr
) implements Indicator, ATR {

}
