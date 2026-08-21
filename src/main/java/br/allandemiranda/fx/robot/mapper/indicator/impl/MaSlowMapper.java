package br.allandemiranda.fx.robot.mapper.indicator.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MaSlowEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaSlowMapper implements IndicatorMapper<MaSlowEntry, MaSlowDto, MaSlowCreateDto> {

  @Override
  public MaSlowDto toDto(MaSlowEntry model) {
    return new MaSlowDto(model.id(), model.eaName(), model.symbolName(), model.timeframe(), model.timestamp(), model.ma());
  }

  @Override
  public MaSlowEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MaSlowCreateDto createDto) {
    return new MaSlowEntry(id, expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), createDto.timestamp(), createDto.ma());
  }
}
