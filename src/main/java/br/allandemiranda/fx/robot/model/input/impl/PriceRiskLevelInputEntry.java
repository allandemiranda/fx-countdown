package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.model.input.PriceRiskLevelInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_price_risk_level")
public record PriceRiskLevelInputEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("k_tp") BigDecimal kTP,
    @Column("k_sl") BigDecimal kSL
) implements Serializable, Input, PriceRiskLevelInput {

}
