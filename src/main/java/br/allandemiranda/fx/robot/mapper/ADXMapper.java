package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.ADX;
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
