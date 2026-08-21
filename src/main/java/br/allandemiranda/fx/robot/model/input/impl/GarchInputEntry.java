package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.model.input.GarchInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_garch")
public record GarchInputEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("horizon") int horizon,
    @Column("price_size") int priceSize
) implements Serializable, Input, GarchInput {

}
