package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IADX;
import org.springframework.stereotype.Component;

@Component
public final class IADXMapper implements InputObjectMapper<IADX, IADXDto, IADXCreateDto> {

  public IADXDto toDto(ChartDto chartDto, IADX iADX) {
    return new IADXDto(chartDto, iADX.period());
  }

  public IADX toModel(ChartDto chartDto, IADXCreateDto iADXCreateDto) {
    return new IADX(chartDto.id(), iADXCreateDto.period());
  }
}
