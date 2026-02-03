package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IRSI;
import org.springframework.stereotype.Component;

@Component
public final class IRSIMapper implements InputObjectMapper<IRSI, IRSIDto, IRSICreateDto> {

  public IRSIDto toDto(ChartDto chartDto, IRSI iRSI) {
    return new IRSIDto(chartDto, iRSI.period(), iRSI.applyTo());
  }

  public IRSI toModel(ChartDto chartDto, IRSICreateDto iRSICreateDto) {
    return new IRSI(chartDto.id(), iRSICreateDto.period(), iRSICreateDto.applyTo());
  }
}
