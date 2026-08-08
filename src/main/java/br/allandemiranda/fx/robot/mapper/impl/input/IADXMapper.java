package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IADX;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IADXMapper implements InputMapper<IADX, IADXDto, IADXCreateDto> {

  public IADXDto toDto(ExpertAdvisorDto expertAdvisorDto, IADX iADX) {
    return new IADXDto(iADX.id(), expertAdvisorDto, iADX.period());
  }

  public IADX toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IADXCreateDto iADXCreateDto) {
    return new IADX(id, expertAdvisorDto.id(), iADXCreateDto.period());
  }
}
