package br.allandemiranda.fx.robot.dto.impl.base;

import br.allandemiranda.fx.robot.dto.BaseDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.InputObjectDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public record MLInputDto(@Valid @NotNull ChartDto chartDto, @Positive int chartObjectNum, @Positive int maxDepth, @Positive float eta, @Positive float subsample, @Positive float colSampleByTree, @Positive int minChildWeight, @PositiveOrZero float lambda, @PositiveOrZero float alpha) implements
    Serializable, BaseDto, InputObjectDto {

}
