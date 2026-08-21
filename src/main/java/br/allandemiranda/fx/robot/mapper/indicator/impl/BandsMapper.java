package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.BandsDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.BandsEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class BandsMapper implements IndicatorMapper<BandsEntry, BandsDto, BandsCreateDto> {

  @Override
  public BandsDto toDto(BandsEntry model) {
    return new BandsDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.baseLine(), model.upperBand(), model.lowerBand());
  }

  @Override
  public BandsEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, BandsCreateDto createDto) {
    return new BandsEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.baseLine(), createDto.upperBand(), createDto.lowerBand());
  }
}
