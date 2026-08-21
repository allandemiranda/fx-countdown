package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.IStochasticDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IStochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.IStochasticEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IStochasticMapper implements InputMapper<IStochasticEntry, IStochasticDto, IStochasticCreateDto> {

  @Override
  public IStochasticDto toDto(IStochasticEntry iStochastic) {
    return new IStochasticDto(iStochastic.id(), iStochastic.eaName(), iStochastic.kPeriod(), iStochastic.dPeriod(), iStochastic.slowing(), iStochastic.maMethod(), iStochastic.priceField());
  }

  @Override
  public IStochasticEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IStochasticCreateDto iStochasticCreateDto) {
    return new IStochasticEntry(id, expertAdvisorDto.eaName(), iStochasticCreateDto.kPeriod(), iStochasticCreateDto.dPeriod(), iStochasticCreateDto.slowing(), iStochasticCreateDto.maMethod(),
        iStochasticCreateDto.priceField());
  }
}
