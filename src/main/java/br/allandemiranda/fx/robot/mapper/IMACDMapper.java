package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.IMACDDto;
import br.allandemiranda.fx.robot.model.IMACD;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class IMACDMapper {

  public static @NonNull IMACDDto toIMACDDto(@NonNull ChartDto chartDto, @NonNull IMACD iMACD) {
    return new IMACDDto(chartDto, iMACD.fastEma(), iMACD.slowEma(), iMACD.macdSma(), iMACD.applyTo());
  }

  public static @NonNull IMACD toIMACD(@NonNull ChartDto chartDto, @NonNull IMACDCreateDto iMACDCreateDto) {
    return new IMACD(chartDto.id(), iMACDCreateDto.fastEma(), iMACDCreateDto.slowEma(), iMACDCreateDto.macdSma(), iMACDCreateDto.applyTo());
  }
}
