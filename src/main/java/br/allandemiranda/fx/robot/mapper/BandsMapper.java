package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.model.Bands;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BandsMapper {

  public static @NonNull BandsDto toBandsDto(@NonNull ChartDto chartDto, @NonNull Bands bands) {
    return new BandsDto(bands.id(), chartDto, bands.timestamp(), bands.baseLine(), bands.upperBand(), bands.lowerBand());
  }

  public static @NonNull Bands toBands(UUID id, @NonNull ChartDto chartDto, @NonNull BandsCreateDto bandsCreateDto) {
    return new Bands(id, chartDto.id(), bandsCreateDto.timestamp(), bandsCreateDto.baseLine(), bandsCreateDto.upperBand(), bandsCreateDto.lowerBand());
  }

}
