package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IMASlowDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMASlowCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IMASlowEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMASlowMapper implements InputMapper<IMASlowEntry, IMASlowDto, IMASlowCreateDto> {

  @Override
  public IMASlowDto toDto(IMASlowEntry imaSlow) {
    return new IMASlowDto(imaSlow.id(), imaSlow.eaName(), imaSlow.maPeriod(), imaSlow.maShift(), imaSlow.maMethod(), imaSlow.appliedPrice());
  }

  @Override
  public IMASlowEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMASlowCreateDto imaSlowCreateDto) {
    return new IMASlowEntry(id, expertAdvisorDto.eaName(), imaSlowCreateDto.maPeriod(), imaSlowCreateDto.maShift(), imaSlowCreateDto.maMethod(), imaSlowCreateDto.appliedPrice());
  }
}
