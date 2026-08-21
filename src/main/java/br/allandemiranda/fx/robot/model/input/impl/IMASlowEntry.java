package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IMASlow;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_i_ma_slow")
public record IMASlowEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("ma_period") short maPeriod,
    @Column("ma_shift") short maShift,
    @Column("ma_method") SmoothingMethod maMethod,
    @Column("applied_price") AppliedPrice appliedPrice
) implements Serializable, Input, IMASlow {

}
