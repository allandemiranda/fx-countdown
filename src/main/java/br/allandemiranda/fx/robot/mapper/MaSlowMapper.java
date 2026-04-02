package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.MaSlow;
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
