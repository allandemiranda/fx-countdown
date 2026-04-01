package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.ADX;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ADXMapper {

  public static @NonNull ADXDto toADXDto(@NonNull ChartDto chartDto, @NonNull ADX adx) {
    return new ADXDto(adx.id(), chartDto, adx.timestamp(), adx.mainLine(), adx.plusDiLine(), adx.minusDiLine());
  }

  public static @NonNull ADX toADX(UUID id, @NonNull ChartDto chartDto, @NonNull ADXCreateDto adxCreateDto) {
    return new ADX(id, chartDto.id(), adxCreateDto.timestamp(), adxCreateDto.mainLine(), adxCreateDto.plusDiLine(), adxCreateDto.minusDiLine());
  }

}
