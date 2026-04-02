package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.IMACD;
import org.springframework.stereotype.Component;

@Component
public final class IMACDMapper implements InputObjectMapper<IMACD, IMACDDto, IMACDCreateDto> {

  public IMACDDto toDto(ChartDto chartDto, IMACD iMACD) {
    return new IMACDDto(chartDto, iMACD.fastEma(), iMACD.slowEma(), iMACD.macdSma(), iMACD.applyTo());
  }

  public IMACD toModel(ChartDto chartDto, IMACDCreateDto iMACDCreateDto) {
    return new IMACD(chartDto.id(), iMACDCreateDto.fastEma(), iMACDCreateDto.slowEma(), iMACDCreateDto.macdSma(), iMACDCreateDto.applyTo());
  }
}
