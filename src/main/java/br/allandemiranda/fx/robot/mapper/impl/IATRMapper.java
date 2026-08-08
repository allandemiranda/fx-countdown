package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IATR;
import org.springframework.stereotype.Component;

@Component
public final class IATRMapper implements InputObjectMapper<IATR, IATRDto, IATRCreateDto> {

  @Override
  public IATRDto toDto(ChartDto chartDto, IATR iATR) {
    return new IATRDto(chartDto, iATR.period());
  }

  @Override
  public IATR toModel(ChartDto chartDto, IATRCreateDto iATRCreateDto) {
    return new IATR(chartDto.id(), iATRCreateDto.period());
  }
}
