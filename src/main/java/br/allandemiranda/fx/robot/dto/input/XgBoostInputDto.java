package br.allandemiranda.fx.robot.dto.input;

import br.allandemiranda.fx.robot.model.input.XgBoostInput;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import br.allandemiranda.fx.robot.model.input.provider.XGBoostInputParameters;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record XgBoostInputDto(
    UUID id,
    String eaName,
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
) implements Serializable, Input, XgBoostInput, XGBoostInputParameters {

}
