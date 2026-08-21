package br.allandemiranda.fx.robot.model.input.impl;

import br.allandemiranda.fx.robot.model.input.XgBoostInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import br.allandemiranda.fx.robot.model.input.provider.XGBoostInputParameters;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("input_xgboost")
public record XgBoostInputEntry(
    @Id @Column("id") UUID id,
    @Column("expert_advisor_name") String eaName,
    @Column("max_depth") int maxDepth,
    @Column("learning_rate") float eta,
    @Column("subsample") float subsample,
    @Column("colsample_bytree") float colSampleByTree,
    @Column("min_child_weight") int minChildWeight,
    @Column("l2") float lambda,
    @Column("l1") float alpha,
    @Column("rounds") int rounds,
    @Column("stopping_rounds") int earlyStoppingRounds,
    @Column("horizon") int horizon,
    @Column("minimal_level_accepted") BigDecimal minimalLevelAccepted,
    @Column("validation_percentage") BigDecimal validationPercentage
) implements Serializable, Input, XgBoostInput, XGBoostInputParameters {

}
