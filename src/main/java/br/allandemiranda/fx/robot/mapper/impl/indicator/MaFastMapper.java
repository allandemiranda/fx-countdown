package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MaFast;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaFastMapper implements IndicatorMapper<MaFast, MaFastDto, MaFastCreateDto> {

  @Override
  public MaFastDto toDto(ExpertAdvisorDto expertAdvisorDto, MaFast maFast) {
    return new MaFastDto(maFast.id(), expertAdvisorDto, maFast.timestamp(), maFast.ma());
  }

  @Override
  public MaFast toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MaFastCreateDto maFastCreateDto) {
    return new MaFast(id, expertAdvisorDto.id(), maFastCreateDto.timestamp(), maFastCreateDto.ma());
  }

}
