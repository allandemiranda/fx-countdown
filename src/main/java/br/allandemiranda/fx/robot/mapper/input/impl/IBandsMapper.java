package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IBandsDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IBandsEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IBandsMapper implements InputMapper<IBandsEntry, IBandsDto, IBandsCreateDto> {

  @Override
  public IBandsDto toDto(IBandsEntry ibands) {
    return new IBandsDto(ibands.id(), ibands.eaName(), ibands.bandsPeriod(), ibands.bandsShift(), ibands.deviation(), ibands.appliedPrice());
  }

  @Override
  public IBandsEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IBandsCreateDto ibandsCreateDto) {
    return new IBandsEntry(id, expertAdvisorDto.eaName(), ibandsCreateDto.bandsPeriod(), ibandsCreateDto.bandsShift(), ibandsCreateDto.deviation(), ibandsCreateDto.appliedPrice());
  }
}
