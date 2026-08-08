package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IATR;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IATRMapper implements InputMapper<IATR, IATRDto, IATRCreateDto> {

  @Override
  public IATRDto toDto(ExpertAdvisorDto expertAdvisorDto, IATR iATR) {
    return new IATRDto(iATR.id(), expertAdvisorDto, iATR.period());
  }

  @Override
  public IATR toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IATRCreateDto iATRCreateDto) {
    return new IATR(id, expertAdvisorDto.id(), iATRCreateDto.period());
  }
}
