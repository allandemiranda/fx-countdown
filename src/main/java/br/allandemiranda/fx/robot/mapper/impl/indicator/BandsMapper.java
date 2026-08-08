package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.Bands;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class BandsMapper implements IndicatorMapper<Bands, BandsDto, BandsCreateDto> {

  @Override
  public BandsDto toDto(ExpertAdvisorDto expertAdvisorDto, Bands bands) {
    return new BandsDto(bands.id(), expertAdvisorDto, bands.timestamp(), bands.baseLine(), bands.upperBand(), bands.lowerBand());
  }

  @Override
  public Bands toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, BandsCreateDto bandsCreateDto) {
    return new Bands(id, expertAdvisorDto.id(), bandsCreateDto.timestamp(), bandsCreateDto.baseLine(), bandsCreateDto.upperBand(), bandsCreateDto.lowerBand());
  }

}
