package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ATRDto;
import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.ATR;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ATRMapper implements ChartObjectMapper<ATR, ATRDto, ATRCreateDto> {

  @Override
  public ATRDto toDto(ChartDto chartDto, ATR atr) {
    return new ATRDto(atr.id(), chartDto, atr.timestamp(), atr.atr());
  }

  @Override
  public ATR toModel(UUID id, ChartDto chartDto, ATRCreateDto atrCreateDto) {
    return new ATR(id, chartDto.id(), atrCreateDto.timestamp(), atrCreateDto.atr());
  }

}
