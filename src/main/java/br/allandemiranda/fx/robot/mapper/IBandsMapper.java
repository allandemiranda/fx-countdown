package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.IBandsDto;
import br.allandemiranda.fx.robot.model.IBands;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IBandsMapper {

  public static @NonNull IBandsDto toIBandsDto(@NonNull ChartDto chartDto, @NonNull IBands iBands) {
    return new IBandsDto(chartDto, iBands.period(), iBands.shift(), iBands.deviations(), iBands.applyTo());
  }

  public static @NonNull IBands toIBands(@NonNull ChartDto chartDto, @NonNull IBandsCreateDto iBandsCreateDto) {
    return new IBands(chartDto.id(), iBandsCreateDto.period(), iBandsCreateDto.shift(), iBandsCreateDto.deviations(), iBandsCreateDto.applyTo());
  }
}
