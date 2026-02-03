package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.MaSlow;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MaSlowMapper implements ChartObjectMapper<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @Override
  public MaSlowDto toDto(ChartDto chartDto, MaSlow maSlow) {
    return new MaSlowDto(maSlow.id(), chartDto, maSlow.timestamp(), maSlow.ma());
  }

  @Override
  public MaSlow toModel(UUID id, ChartDto chartDto, MaSlowCreateDto maSlowCreateDto) {
    return new MaSlow(id, chartDto.id(), maSlowCreateDto.timestamp(), maSlowCreateDto.ma());
  }

}
