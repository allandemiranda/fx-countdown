package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.model.MaSlow;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MaSlowMapper {

  public static @NonNull MaSlowDto toMaSlowDto(@NonNull ChartDto chartDto, @NonNull MaSlow maSlow) {
    return new MaSlowDto(maSlow.id(), chartDto, maSlow.timestamp(), maSlow.ma());
  }

  public static @NonNull MaSlow toMaSlow(UUID id, @NonNull ChartDto chartDto, @NonNull MaSlowCreateDto maSlowCreateDto) {
    return new MaSlow(id, chartDto.id(), maSlowCreateDto.timestamp(), maSlowCreateDto.ma());
  }

}
