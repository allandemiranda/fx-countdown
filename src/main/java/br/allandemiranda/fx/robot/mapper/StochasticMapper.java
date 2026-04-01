package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.model.Stochastic;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StochasticMapper {

  public static @NonNull StochasticDto toStochasticDto(@NonNull ChartDto chartDto, @NonNull Stochastic stochastic) {
    return new StochasticDto(stochastic.id(), chartDto, stochastic.timestamp(), stochastic.mainLine(), stochastic.signalLine());
  }

  public static @NonNull Stochastic toStochastic(UUID id, @NonNull ChartDto chartDto, @NonNull StochasticCreateDto stochasticCreateDto) {
    return new Stochastic(id, chartDto.id(), stochasticCreateDto.timestamp(), stochasticCreateDto.mainLine(), stochasticCreateDto.signalLine());
  }

}
