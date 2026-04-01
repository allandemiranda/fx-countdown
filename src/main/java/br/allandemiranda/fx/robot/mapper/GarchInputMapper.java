package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.GarchInputDto;
import br.allandemiranda.fx.robot.model.GarchInput;
import lombok.NonNull;

public class GarchInputMapper {

  public static @NonNull GarchInputDto toGarchInputDto(@NonNull ChartDto chartDto, @NonNull GarchInput garchInput) {
    return new GarchInputDto(chartDto, garchInput.horizon(), garchInput.priceSize(), garchInput.kTP(), garchInput.kSL());
  }

  public static @NonNull GarchInput toGarchInput(@NonNull ChartDto chartDto, @NonNull GarchInputCreateDto garchInputCreateDto) {
    return new GarchInput(chartDto.id(), garchInputCreateDto.horizon(), garchInputCreateDto.priceSize(), garchInputCreateDto.kTP(), garchInputCreateDto.kSL());
  }
}
