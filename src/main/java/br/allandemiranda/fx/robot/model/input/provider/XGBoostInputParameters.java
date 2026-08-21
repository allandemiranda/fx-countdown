package br.allandemiranda.fx.robot.model.input.provider;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public interface XGBoostInputParameters {

  @PositiveOrZero
  float alpha();

  @Positive
  float colSampleByTree();

  @PositiveOrZero
  int earlyStoppingRounds();

  @Positive
  float eta();

  @PositiveOrZero
  float lambda();

  @Positive
  int maxDepth();

  @Positive
  int minChildWeight();

  @Positive
  int rounds();

  @Positive
  float subsample();
}
