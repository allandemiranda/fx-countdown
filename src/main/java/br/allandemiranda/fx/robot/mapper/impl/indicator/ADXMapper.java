package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.ADX;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ADXMapper implements IndicatorMapper<ADX, ADXDto, ADXCreateDto> {

  @Override
  public ADXDto toDto(ExpertAdvisorDto expertAdvisorDto, ADX model) {
    return new ADXDto(model.id(), expertAdvisorDto, model.timestamp(), model.mainLine(), model.plusDiLine(), model.minusDiLine());
  }

  @Override
  public ADX toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, ADXCreateDto createDto) {
    return new ADX(id, expertAdvisorDto.id(), createDto.timestamp(), createDto.mainLine(), createDto.plusDiLine(), createDto.minusDiLine());
  }
}
