package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.dto.GarchTradingDto;
import br.allandemiranda.fx.robot.model.GarchTrading;
import java.util.UUID;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GarchTradingMapper {

  public static @NonNull GarchTradingDto toGarchTradingDto(@NonNull ChartDto chartDto, @NonNull GarchTrading garchTrading) {
    return new GarchTradingDto(garchTrading.id(), chartDto, garchTrading.timestamp(), garchTrading.buyOpenPrice(), garchTrading.buyTpPrice(), garchTrading.buySlPrice(), garchTrading.buyPoints(), garchTrading.closeBuyTime(), garchTrading.buyDealReason(), garchTrading.sellOpenPrice(),
        garchTrading.sellTpPrice(), garchTrading.sellSlPrice(), garchTrading.sellPoints(), garchTrading.closeSellTime(), garchTrading.sellDealReason());
  }

  public static @NonNull GarchTrading toGarchTrading(UUID id, @NonNull ChartDto chartDto, @NonNull GarchTradingCreateDto garchTradingCreateDto) {
    return new GarchTrading(id, chartDto.id(), garchTradingCreateDto.timestamp(), garchTradingCreateDto.buyOpenPrice(), garchTradingCreateDto.buyTpPrice(), garchTradingCreateDto.buySlPrice(), 0, null, null, garchTradingCreateDto.sellOpenPrice(),
        garchTradingCreateDto.sellTpPrice(), garchTradingCreateDto.sellSlPrice(), 0, null, null);
  }

}
