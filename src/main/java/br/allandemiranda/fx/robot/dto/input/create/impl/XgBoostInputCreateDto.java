package br.allandemiranda.fx.robot.dto.input.create.impl;

import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.XgBoostInput;
import br.allandemiranda.fx.robot.model.input.provider.XGBoostInputParameters;
import java.io.Serializable;
import java.math.BigDecimal;

public record XgBoostInputCreateDto(
    int maxDepth,
    float eta,
    float subsample,
    float colSampleByTree,
    int minChildWeight,
    float lambda,
    float alpha,
    int rounds,
    int earlyStoppingRounds,
    int horizon,
    BigDecimal minimalLevelAccepted,
    BigDecimal validationPercentage
) implements Serializable, InputCreate, XgBoostInput, XGBoostInputParameters {

}
