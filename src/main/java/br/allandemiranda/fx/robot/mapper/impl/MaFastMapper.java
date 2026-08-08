package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.MaFast;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaFastMapper implements ChartObjectMapper<MaFast, MaFastDto, MaFastCreateDto> {

  @Override
  public MaFastDto toDto(ChartDto chartDto, MaFast maFast) {
    return new MaFastDto(maFast.id(), chartDto, maFast.timestamp(), maFast.ma());
  }

  @Override
  public MaFast toModel(UUID id, ChartDto chartDto, MaFastCreateDto maFastCreateDto) {
    return new MaFast(id, chartDto.id(), maFastCreateDto.timestamp(), maFastCreateDto.ma());
  }

}
