package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.create.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.base.BandsDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.Bands;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class BandsMapper implements ChartObjectMapper<Bands, BandsDto, BandsCreateDto> {

  @Override
  public BandsDto toDto(ChartDto chartDto, Bands bands) {
    return new BandsDto(bands.id(), chartDto, bands.timestamp(), bands.baseLine(), bands.upperBand(), bands.lowerBand());
  }

  @Override
  public Bands toModel(UUID id, ChartDto chartDto, BandsCreateDto bandsCreateDto) {
    return new Bands(id, chartDto.id(), bandsCreateDto.timestamp(), bandsCreateDto.baseLine(), bandsCreateDto.upperBand(), bandsCreateDto.lowerBand());
  }

}
