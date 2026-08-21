package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IMACD;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_i_macd")
public record IMACDEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("fast_ema_period") short fastEmaPeriod,
    @Column("slow_ema_period") short slowEmaPeriod,
    @Column("signal_period") short signalPeriod,
    @Column("applied_price") AppliedPrice appliedPrice
) implements Serializable, Input, IMACD {

}
