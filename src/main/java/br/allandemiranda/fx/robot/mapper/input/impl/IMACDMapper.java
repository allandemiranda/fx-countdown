package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IMACDDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IMACDEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMACDMapper implements InputMapper<IMACDEntry, IMACDDto, IMACDCreateDto> {

  @Override
  public IMACDDto toDto(IMACDEntry imacd) {
    return new IMACDDto(imacd.id(), imacd.eaName(), imacd.fastEmaPeriod(), imacd.slowEmaPeriod(), imacd.signalPeriod(), imacd.appliedPrice());
  }

  @Override
  public IMACDEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMACDCreateDto imacdCreateDto) {
    return new IMACDEntry(id, expertAdvisorDto.eaName(), imacdCreateDto.fastEmaPeriod(), imacdCreateDto.slowEmaPeriod(), imacdCreateDto.signalPeriod(), imacdCreateDto.appliedPrice());
  }
}
