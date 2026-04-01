package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.IMAFastDto;
import br.allandemiranda.fx.robot.model.IMAFast;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IMAFastMapper {

  public static @NonNull IMAFastDto toIMAFastDto(@NonNull ChartDto chartDto, @NonNull IMAFast iMAFast) {
    return new IMAFastDto(chartDto, iMAFast.period(), iMAFast.shift(), iMAFast.method(), iMAFast.applyTo());
  }

  public static @NonNull IMAFast toIMAFast(@NonNull ChartDto chartDto, @NonNull IMAFastCreateDto iMAFastCreateDto) {
    return new IMAFast(chartDto.id(), iMAFastCreateDto.period(), iMAFastCreateDto.shift(), iMAFastCreateDto.method(), iMAFastCreateDto.applyTo());
  }
}
