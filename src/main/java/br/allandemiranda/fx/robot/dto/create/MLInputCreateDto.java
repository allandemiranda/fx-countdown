package br.allandemiranda.fx.robot.dto.create;

import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public record MLInputCreateDto(@Positive int chartObjectNum, @Positive int maxDepth, @Positive float eta, @Positive float subsample, @Positive float colSampleByTree, @Positive int minChildWeight, @PositiveOrZero float lambda, @PositiveOrZero float alpha) implements Serializable,
    CreateInputObjectDto {

}
