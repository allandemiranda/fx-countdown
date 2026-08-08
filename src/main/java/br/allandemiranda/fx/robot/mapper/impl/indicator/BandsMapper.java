package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.Bands;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class BandsMapper implements IndicatorMapper<Bands, BandsDto, BandsCreateDto> {

  @Override
  public BandsDto toDto(DashboardDto dashboardDto, Bands bands) {
    return new BandsDto(bands.id(), dashboardDto, bands.timestamp(), bands.baseLine(), bands.upperBand(), bands.lowerBand());
  }

  @Override
  public Bands toModel(UUID id, DashboardDto dashboardDto, BandsCreateDto bandsCreateDto) {
    return new Bands(id, dashboardDto.id(), bandsCreateDto.timestamp(), bandsCreateDto.baseLine(), bandsCreateDto.upperBand(), bandsCreateDto.lowerBand());
  }

}
