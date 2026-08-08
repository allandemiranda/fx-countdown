package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ADXDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.ADX;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ADXMapper implements ChartObjectMapper<ADX, ADXDto, ADXCreateDto> {

  @Override
  public ADXDto toDto(ChartDto chartDto, ADX model) {
    return new ADXDto(model.id(), chartDto, model.timestamp(), model.mainLine(), model.plusDiLine(), model.minusDiLine());
  }

  @Override
  public ADX toModel(UUID id, ChartDto chartDto, ADXCreateDto createDto) {
    return new ADX(id, chartDto.id(), createDto.timestamp(), createDto.mainLine(), createDto.plusDiLine(), createDto.minusDiLine());
  }

}
