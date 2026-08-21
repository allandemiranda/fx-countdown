package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.MaFastDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MaFastEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaFastMapper implements IndicatorMapper<MaFastEntry, MaFastDto, MaFastCreateDto> {

  @Override
  public MaFastDto toDto(MaFastEntry model) {
    return new MaFastDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.ma());
  }

  @Override
  public MaFastEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MaFastCreateDto createDto) {
    return new MaFastEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.ma());
  }
}
