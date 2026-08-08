package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSIDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IRSI;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IRSIMapper implements InputMapper<IRSI, IRSIDto, IRSICreateDto> {

  public IRSIDto toDto(ExpertAdvisorDto expertAdvisorDto, IRSI iRSI) {
    return new IRSIDto(iRSI.id(), expertAdvisorDto, iRSI.period(), iRSI.applyTo());
  }

  public IRSI toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IRSICreateDto iRSICreateDto) {
    return new IRSI(id, expertAdvisorDto.id(), iRSICreateDto.period(), iRSICreateDto.applyTo());
  }
}
