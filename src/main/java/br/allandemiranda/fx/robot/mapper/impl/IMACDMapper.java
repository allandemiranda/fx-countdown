package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.IMACD;
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
