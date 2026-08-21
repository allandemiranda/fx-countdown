package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IADXDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IADXEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IADXMapper implements InputMapper<IADXEntry, IADXDto, IADXCreateDto> {

  @Override
  public IADXDto toDto(IADXEntry iadx) {
    return new IADXDto(iadx.id(), iadx.eaName(), iadx.adxPeriod());
  }

  @Override
  public IADXEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IADXCreateDto iadxCreateDto) {
    return new IADXEntry(id, expertAdvisorDto.eaName(), iadxCreateDto.adxPeriod());
  }
}
