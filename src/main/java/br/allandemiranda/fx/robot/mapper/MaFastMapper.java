package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.model.MaFast;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MaFastMapper {

  public static @NonNull MaFastDto toMaFastDto(@NonNull ChartDto chartDto, @NonNull MaFast maFast) {
    return new MaFastDto(maFast.id(), chartDto, maFast.timestamp(), maFast.ma());
  }

  public static @NonNull MaFast toMaFast(UUID id, @NonNull ChartDto chartDto, @NonNull MaFastCreateDto maFastCreateDto) {
    return new MaFast(id, chartDto.id(), maFastCreateDto.timestamp(), maFastCreateDto.ma());
  }

}
