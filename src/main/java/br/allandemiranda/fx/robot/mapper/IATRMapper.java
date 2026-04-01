package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.IATRDto;
import br.allandemiranda.fx.robot.model.IATR;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IATRMapper {

  public static @NonNull IATRDto toIATRDto(@NonNull ChartDto chartDto, @NonNull IATR iATR) {
    return new IATRDto(chartDto, iATR.period());
  }

  public static @NonNull IATR toIATR(@NonNull ChartDto chartDto, @NonNull IATRCreateDto iATRCreateDto) {
    return new IATR(chartDto.id(), iATRCreateDto.period());
  }
}
