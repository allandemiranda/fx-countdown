package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IRSI;
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
