package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IMAFastDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMAFastCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IMAFastEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMAFastMapper implements InputMapper<IMAFastEntry, IMAFastDto, IMAFastCreateDto> {

  @Override
  public IMAFastDto toDto(IMAFastEntry imaFast) {
    return new IMAFastDto(imaFast.id(), imaFast.eaName(), imaFast.maPeriod(), imaFast.maShift(), imaFast.maMethod(), imaFast.appliedPrice());
  }

  @Override
  public IMAFastEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMAFastCreateDto imaFastCreateDto) {
    return new IMAFastEntry(id, expertAdvisorDto.eaName(), imaFastCreateDto.maPeriod(), imaFastCreateDto.maShift(), imaFastCreateDto.maMethod(), imaFastCreateDto.appliedPrice());
  }
}
