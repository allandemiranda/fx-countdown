package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMACD;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IMACDMapper implements InputMapper<IMACD, IMACDDto, IMACDCreateDto> {

  public IMACDDto toDto(ExpertAdvisorDto expertAdvisorDto, IMACD iMACD) {
    return new IMACDDto(iMACD.id(), expertAdvisorDto, iMACD.fastEma(), iMACD.slowEma(), iMACD.macdSma(), iMACD.applyTo());
  }

  public IMACD toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IMACDCreateDto iMACDCreateDto) {
    return new IMACD(id, expertAdvisorDto.id(), iMACDCreateDto.fastEma(), iMACDCreateDto.slowEma(), iMACDCreateDto.macdSma(), iMACDCreateDto.applyTo());
  }
}
