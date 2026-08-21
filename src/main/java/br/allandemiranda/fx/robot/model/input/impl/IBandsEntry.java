package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.model.input.IBands;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_i_bands")
public record IBandsEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("bands_period") short bandsPeriod,
    @Column("bands_shift") short bandsShift,
    @Column("deviation") BigDecimal deviation,
    @Column("applied_price") AppliedPrice appliedPrice
) implements Serializable, Input, IBands {

}
