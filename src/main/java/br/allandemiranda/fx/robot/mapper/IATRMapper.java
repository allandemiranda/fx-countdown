package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.base.IATRDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IATR;
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
