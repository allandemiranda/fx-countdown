package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IADX;
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
