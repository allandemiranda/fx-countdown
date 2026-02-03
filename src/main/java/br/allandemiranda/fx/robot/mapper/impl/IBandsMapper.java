package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IBands;
import org.springframework.stereotype.Component;

@Component
public final class IBandsMapper implements InputObjectMapper<IBands, IBandsDto, IBandsCreateDto> {

  public IBandsDto toDto(ChartDto chartDto, IBands iBands) {
    return new IBandsDto(chartDto, iBands.period(), iBands.shift(), iBands.deviations(), iBands.applyTo());
  }

  public IBands toModel(ChartDto chartDto, IBandsCreateDto iBandsCreateDto) {
    return new IBands(chartDto.id(), iBandsCreateDto.period(), iBandsCreateDto.shift(), iBandsCreateDto.deviations(), iBandsCreateDto.applyTo());
  }
}
