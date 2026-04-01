package br.allandemiranda.fx.robot.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ml_input")
public record MLInput(@Id @Column("chart_id") @NotNull UUID chartId, @Column("horizon") @Positive int chartObjectNum, @Column("max_depth") @Positive int maxDepth, @Column("learning_rate") @Positive float eta, @Column("subsample") @Positive float subsample,
                      @Column("col_sample_by_tree") @Positive float colSampleByTree, @Column("min_child_weight") @Positive int minChildWeight, @Column("l2") @PositiveOrZero float lambda, @Column("l1") @PositiveOrZero float alpha) {

}
