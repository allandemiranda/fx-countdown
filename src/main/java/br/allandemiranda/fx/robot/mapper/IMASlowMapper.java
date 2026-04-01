package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.IMASlowDto;
import br.allandemiranda.fx.robot.model.IMASlow;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IMASlowMapper {

  public static @NonNull IMASlowDto toIMASlowDto(@NonNull ChartDto chartDto, @NonNull IMASlow iMASlow) {
    return new IMASlowDto(chartDto, iMASlow.period(), iMASlow.shift(), iMASlow.method(), iMASlow.applyTo());
  }

  public static @NonNull IMASlow toIMASlow(@NonNull ChartDto chartDto, @NonNull IMASlowCreateDto iMASlowCreateDto) {
    return new IMASlow(chartDto.id(), iMASlowCreateDto.period(), iMASlowCreateDto.shift(), iMASlowCreateDto.method(), iMASlowCreateDto.applyTo());
  }
}
