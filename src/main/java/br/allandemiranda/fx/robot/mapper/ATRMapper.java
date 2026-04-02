package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.ATR;
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
