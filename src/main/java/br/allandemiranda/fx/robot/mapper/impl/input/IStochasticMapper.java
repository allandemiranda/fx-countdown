package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.IStochastic;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IStochasticMapper implements InputMapper<IStochastic, IStochasticDto, IStochasticCreateDto> {

  public IStochasticDto toDto(ExpertAdvisorDto expertAdvisorDto, IStochastic iStochastic) {
    return new IStochasticDto(iStochastic.id(), expertAdvisorDto, iStochastic.kPeriod(), iStochastic.dPeriod(), iStochastic.slowing(), iStochastic.method(), iStochastic.priceField());
  }

  public IStochastic toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, IStochasticCreateDto iStochasticCreateDto) {
    return new IStochastic(id, expertAdvisorDto.id(), iStochasticCreateDto.kPeriod(), iStochasticCreateDto.dPeriod(), iStochasticCreateDto.slowing(), iStochasticCreateDto.method(), iStochasticCreateDto.priceField());
  }
}
