package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.MACD;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MACDMapper implements ChartObjectMapper<MACD, MACDDto, MACDCreateDto> {

  @Override
  public MACDDto toDto(ChartDto chartDto, MACD macd) {
    return new MACDDto(macd.id(), chartDto, macd.timestamp(), macd.mainLine(), macd.signalLine());
  }

  @Override
  public MACD toModel(UUID id, ChartDto chartDto, MACDCreateDto macdCreateDto) {
    return new MACD(id, chartDto.id(), macdCreateDto.timestamp(), macdCreateDto.mainLine(), macdCreateDto.signalLine());
  }

}
