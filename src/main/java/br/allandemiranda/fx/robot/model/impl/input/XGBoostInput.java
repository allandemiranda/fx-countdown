package br.allandemiranda.fx.robot.model.impl.input;

import br.allandemiranda.fx.robot.model.InputDashboardModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("xgboost_input")
public record XGBoostInput(@Id @Column("chart_id") @NotNull UUID id, @Column("dashboard_id") @NotNull UUID dashboardId, @Column("horizon") @Positive int horizon, @Column("max_depth") @Positive int maxDepth,
                           @Column("learning_rate") @Positive float eta, @Column("subsample") @Positive float subsample, @Column("col_sample_by_tree") @Positive float colSampleByTree,
                           @Column("min_child_weight") @Positive int minChildWeight, @Column("l2") @PositiveOrZero float lambda, @Column("l1") @PositiveOrZero float alpha, @Column("version_file") @PositiveOrZero int versionFile,
                           @Column("minimal_level_accepted") @Positive @Max(100) BigDecimal minimalLevelAccepted) implements InputDashboardModel {

}
