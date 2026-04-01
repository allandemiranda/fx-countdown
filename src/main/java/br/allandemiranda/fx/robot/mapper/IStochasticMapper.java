package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.IStochasticDto;
import br.allandemiranda.fx.robot.model.IStochastic;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IStochasticMapper {

  public static @NonNull IStochasticDto toIStochasticDto(@NonNull ChartDto chartDto, @NonNull IStochastic iStochastic) {
    return new IStochasticDto(chartDto, iStochastic.kPeriod(), iStochastic.dPeriod(), iStochastic.slowing(), iStochastic.method(), iStochastic.priceField());
  }

  public static @NonNull IStochastic toIStochastic(@NonNull ChartDto chartDto, @NonNull IStochasticCreateDto iStochasticCreateDto) {
    return new IStochastic(chartDto.id(), iStochasticCreateDto.kPeriod(), iStochasticCreateDto.dPeriod(), iStochasticCreateDto.slowing(), iStochasticCreateDto.method(), iStochasticCreateDto.priceField());
  }
}
