package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.IRSIDto;
import br.allandemiranda.fx.robot.model.IRSI;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IRSIMapper {

  public static @NonNull IRSIDto toIRSIDto(@NonNull ChartDto chartDto, @NonNull IRSI iRSI) {
    return new IRSIDto(chartDto, iRSI.period(), iRSI.applyTo());
  }

  public static @NonNull IRSI toIRSI(@NonNull ChartDto chartDto, @NonNull IRSICreateDto iRSICreateDto) {
    return new IRSI(chartDto.id(), iRSICreateDto.period(), iRSICreateDto.applyTo());
  }
}
