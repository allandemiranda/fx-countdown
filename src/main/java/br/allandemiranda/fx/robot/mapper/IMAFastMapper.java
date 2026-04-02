package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.base.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IMAFast;
import org.springframework.stereotype.Component;

@Component
public final class IMAFastMapper implements InputObjectMapper<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Override
  public IMAFastDto toDto(ChartDto chartDto, IMAFast iMAFast) {
    return new IMAFastDto(chartDto, iMAFast.period(), iMAFast.shift(), iMAFast.method(), iMAFast.applyTo());
  }

  @Override
  public IMAFast toModel(ChartDto chartDto, IMAFastCreateDto iMAFastCreateDto) {
    return new IMAFast(chartDto.id(), iMAFastCreateDto.period(), iMAFastCreateDto.shift(), iMAFastCreateDto.method(), iMAFastCreateDto.applyTo());
  }
}
