package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.IADXDto;
import br.allandemiranda.fx.robot.model.IADX;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IADXMapper {

  public static @NonNull IADXDto toIADXDto(@NonNull ChartDto chartDto, @NonNull IADX iADX) {
    return new IADXDto(chartDto, iADX.period());
  }

  public static @NonNull IADX toIADX(@NonNull ChartDto chartDto, @NonNull IADXCreateDto iADXCreateDto) {
    return new IADX(chartDto.id(), iADXCreateDto.period());
  }
}
