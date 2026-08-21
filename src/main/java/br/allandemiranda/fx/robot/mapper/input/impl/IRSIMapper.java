package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IRSIDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IRSIEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IRSIMapper implements InputMapper<IRSIEntry, IRSIDto, IRSICreateDto> {

  @Override
  public IRSIDto toDto(IRSIEntry irsi) {
    return new IRSIDto(irsi.id(), irsi.eaName(), irsi.maPeriod(), irsi.appliedPrice());
  }

  @Override
  public IRSIEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IRSICreateDto irsiCreateDto) {
    return new IRSIEntry(id, expertAdvisorDto.eaName(), irsiCreateDto.maPeriod(), irsiCreateDto.appliedPrice());
  }
}
