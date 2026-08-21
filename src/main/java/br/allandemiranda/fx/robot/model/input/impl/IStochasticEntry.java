package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.model.input.IStochastic;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_i_stochastic")
public record IStochasticEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("k_period") short kPeriod,
    @Column("d_period") short dPeriod,
    @Column("slowing") short slowing,
    @Column("ma_method") SmoothingMethod maMethod,
    @Column("price_field") PriceField priceField
) implements Serializable, Input, IStochastic {

}
