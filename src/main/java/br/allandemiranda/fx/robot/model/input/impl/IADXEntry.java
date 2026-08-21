package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.model.input.IADX;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_i_adx")
public record IADXEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("adx_period") short adxPeriod
) implements Serializable, Input, IADX {

}
