package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IBands;
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
