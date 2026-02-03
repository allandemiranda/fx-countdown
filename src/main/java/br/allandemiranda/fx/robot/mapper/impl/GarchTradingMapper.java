package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.base.TickDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.enums.PositionType;
import br.allandemiranda.fx.robot.mapper.ChartObjectMapper;
import br.allandemiranda.fx.robot.model.impl.GarchTrading;
import br.allandemiranda.fx.robot.util.TradingUtils;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchTradingMapper implements ChartObjectMapper<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @Override
  public GarchTradingDto toDto(ChartDto chartDto, GarchTrading garchTrading) {
    return new GarchTradingDto(
        garchTrading.id(),
        chartDto,
        garchTrading.timestamp(),

        garchTrading.openTime(),

        garchTrading.buyOpenPrice(),
        garchTrading.buyTpPrice(),
        garchTrading.buySlPrice(),
        garchTrading.buyPoints(),
        garchTrading.closeBuyPrice(),
        garchTrading.closeBuyTime(),
        garchTrading.buyDealReason(),

        garchTrading.sellOpenPrice(),
        garchTrading.sellTpPrice(),
        garchTrading.sellSlPrice(),
        garchTrading.sellPoints(),
        garchTrading.closeSellPrice(),
        garchTrading.closeSellTime(),
        garchTrading.sellDealReason()
    );
  }

  @Override
  public GarchTrading toModel(UUID id, ChartDto chartDto, GarchTradingCreateDto garchTradingCreateDto) {
    SymbolDto symbolDto = chartDto.symbol();
    TickDto openTick = garchTradingCreateDto.tickOpen();
    BigDecimal spread = TradingUtils.getSpread(openTick.ask(), openTick.bid(), symbolDto.point()).negate();

    return new GarchTrading(
        id,
        chartDto.id(),
        garchTradingCreateDto.timestamp(),

        openTick.timestamp(),

        openTick.ask(),
        garchTradingCreateDto.buyTpPrice(),
        garchTradingCreateDto.buySlPrice(),
        spread,
        openTick.bid(),
        openTick.timestamp(),
        TradingUtils.getDealReason(openTick.ask(), openTick.bid(), garchTradingCreateDto.buyTpPrice(), garchTradingCreateDto.buySlPrice(), PositionType.POSITION_TYPE_BUY),

        openTick.bid(),
        garchTradingCreateDto.sellTpPrice(),
        garchTradingCreateDto.sellSlPrice(),
        spread,
        openTick.ask(),
        openTick.timestamp(),
        TradingUtils.getDealReason(openTick.ask(), openTick.bid(), garchTradingCreateDto.sellTpPrice(), garchTradingCreateDto.sellSlPrice(), PositionType.POSITION_TYPE_SELL)
    );
  }

  public GarchTrading toModel(GarchTradingDto garchTradingDto) {
    return new GarchTrading(
        garchTradingDto.id(),
        garchTradingDto.chartDto().id(),
        garchTradingDto.timestamp(),

        garchTradingDto.openTime(),

        garchTradingDto.buyOpenPrice(),
        garchTradingDto.buyTpPrice(),
        garchTradingDto.buySlPrice(),
        garchTradingDto.buyPoints(),
        garchTradingDto.closeBuyPrice(),
        garchTradingDto.closeBuyTime(),
        garchTradingDto.buyDealReason(),

        garchTradingDto.sellOpenPrice(),
        garchTradingDto.sellTpPrice(),
        garchTradingDto.sellSlPrice(),
        garchTradingDto.sellPoints(),
        garchTradingDto.closeSellPrice(),
        garchTradingDto.closeSellTime(),
        garchTradingDto.sellDealReason()
    );
  }

}
