package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.mapper.impl.GarchTradingMapper;
import br.allandemiranda.fx.robot.model.impl.GarchTrading;
import br.allandemiranda.fx.robot.repository.impl.GarchTradingRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@Getter
@AllArgsConstructor
public class GarchTradingService implements ChartObjectService<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  private final GarchTradingRepository repository;
  private final GarchTradingMapper mapper;

  public Mono<GarchTradingDto> updateTradingValues(ChartDto chartDto, OffsetDateTime timestamp, BigDecimal buyPoints, BigDecimal closeBuyPrice, OffsetDateTime closeBuyTime, DealReason buyDealReason, BigDecimal sellPoints, BigDecimal closeSellPrice, OffsetDateTime closeSellTime, DealReason buySellReason) {
    return this.get(chartDto, timestamp).map(garchTradingDto -> new GarchTrading(
        garchTradingDto.id(),
        garchTradingDto.chartDto().id(),
        garchTradingDto.timestamp(),

        garchTradingDto.openTime(),

        garchTradingDto.buyOpenPrice(),
        garchTradingDto.buyTpPrice(),
        garchTradingDto.buySlPrice(),
        buyPoints,
        closeBuyPrice,
        closeBuyTime,
        buyDealReason,

        garchTradingDto.sellOpenPrice(),
        garchTradingDto.sellTpPrice(),
        garchTradingDto.sellSlPrice(),
        sellPoints,
        closeSellPrice,
        closeSellTime,
        buySellReason
    )).flatMap(garchTrading -> {
      log.info("Update Garch Trading [chartDto={}, timestamp={}]: garchTrading={}", chartDto, timestamp, garchTrading);
      return this.getRepository().save(garchTrading);
    }).map(garchTrading -> this.getMapper().toDto(chartDto, garchTrading));
  }

}
