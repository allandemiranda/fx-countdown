package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MaSlow;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaSlowMapper implements IndicatorMapper<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @Override
  public MaSlowDto toDto(ExpertAdvisorDto expertAdvisorDto, MaSlow maSlow) {
    return new MaSlowDto(maSlow.id(), expertAdvisorDto, maSlow.timestamp(), maSlow.ma());
  }

  @Override
  public MaSlow toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, MaSlowCreateDto maSlowCreateDto) {
    return new MaSlow(id, expertAdvisorDto.id(), maSlowCreateDto.timestamp(), maSlowCreateDto.ma());
  }

}
