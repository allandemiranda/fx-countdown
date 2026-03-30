package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public record MLInput(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("horizon") @Positive int chartObjectNum, @Column("max_depth") @Positive int maxDepth, @Column("learning_rate") @Positive float eta, @Column("subsample") @Positive float subsample,
                      @Column("colsample_bytree") @Positive float colsampleBytree, @Column("min_child_weight") @Positive int minChildWeight, @Column("l2") @PositiveOrZero float lambda,  @Column("l1") @PositiveOrZero float alpha) {

}
