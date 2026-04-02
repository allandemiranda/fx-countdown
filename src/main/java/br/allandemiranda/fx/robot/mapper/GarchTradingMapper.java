package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.GarchTrading;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchTradingMapper implements ChartObjectMapper<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @Override
  public GarchTradingDto toDto(ChartDto chartDto, GarchTrading garchTrading) {
    return new GarchTradingDto(garchTrading.id(), chartDto, garchTrading.timestamp(), garchTrading.buyOpenPrice(), garchTrading.buyTpPrice(), garchTrading.buySlPrice(), garchTrading.buyPoints(), garchTrading.closeBuyTime(), garchTrading.buyDealReason(), garchTrading.sellOpenPrice(),
        garchTrading.sellTpPrice(), garchTrading.sellSlPrice(), garchTrading.sellPoints(), garchTrading.closeSellTime(), garchTrading.sellDealReason());
  }

  @Override
  public GarchTrading toModel(UUID id, ChartDto chartDto, GarchTradingCreateDto garchTradingCreateDto) {
    return new GarchTrading(id, chartDto.id(), garchTradingCreateDto.timestamp(), garchTradingCreateDto.buyOpenPrice(), garchTradingCreateDto.buyTpPrice(), garchTradingCreateDto.buySlPrice(), 0, null, null, garchTradingCreateDto.sellOpenPrice(),
        garchTradingCreateDto.sellTpPrice(), garchTradingCreateDto.sellSlPrice(), 0, null, null);
  }

}
