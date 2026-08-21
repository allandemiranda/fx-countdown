package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IATRDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IATREntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IATRMapper implements InputMapper<IATREntry, IATRDto, IATRCreateDto> {

  @Override
  public IATRDto toDto(IATREntry iatr) {
    return new IATRDto(iatr.id(), iatr.eaName(), iatr.maPeriod());
  }

  @Override
  public IATREntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IATRCreateDto iatrCreateDto) {
    return new IATREntry(id, expertAdvisorDto.eaName(), iatrCreateDto.maPeriod());
  }
}
