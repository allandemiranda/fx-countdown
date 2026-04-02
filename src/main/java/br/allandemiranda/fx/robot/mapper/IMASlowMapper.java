package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.base.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IMASlow;
import org.springframework.stereotype.Component;

@Component
public final class IMASlowMapper implements InputObjectMapper<IMASlow, IMASlowDto, IMASlowCreateDto> {

  @Override
  public IMASlowDto toDto(ChartDto chartDto, IMASlow iMASlow) {
    return new IMASlowDto(chartDto, iMASlow.period(), iMASlow.shift(), iMASlow.method(), iMASlow.applyTo());
  }

  @Override
  public IMASlow toModel(ChartDto chartDto, IMASlowCreateDto iMASlowCreateDto) {
    return new IMASlow(chartDto.id(), iMASlowCreateDto.period(), iMASlowCreateDto.shift(), iMASlowCreateDto.method(), iMASlowCreateDto.applyTo());
  }
}
