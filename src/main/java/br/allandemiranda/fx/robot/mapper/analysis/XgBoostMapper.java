package br.allandemiranda.fx.robot.mapper.analysis;

import br.allandemiranda.fx.robot.dto.analysis.XgBoostDto;
import br.allandemiranda.fx.robot.dto.analysis.create.XgBoostCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.analysis.impl.XgBoostEntry;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Component;

@NullMarked
@Component
public final class XgBoostMapper {

  @Contract("_ -> new")
  public XgBoostDto toDto(XgBoostEntry xgBoostEntry) {
    return new XgBoostDto(xgBoostEntry.symbolName(), xgBoostEntry.timeframe(), xgBoostEntry.eaName(), xgBoostEntry.version(), xgBoostEntry.dataSetSizeBuy(), xgBoostEntry.dataSetSizeSell(), xgBoostEntry.lastTimestampTrainBuy(),
        xgBoostEntry.lastTimestampTrainSell(), xgBoostEntry.createdAt());
  }

  @Contract("_, _, _, _, _ -> new")
  public XgBoostEntry toModel(UUID id, String symbolName, Timeframe timeframe, String eaName, XgBoostCreateDto xgBoostCreateDto) {
    return new XgBoostEntry(id, symbolName, timeframe, eaName, xgBoostCreateDto.version(), xgBoostCreateDto.modelDataBuy(), xgBoostCreateDto.modelDataSell(), xgBoostCreateDto.dataSetSizeBuy(), xgBoostCreateDto.dataSetSizeSell(),
        xgBoostCreateDto.lastTimestampTrainBuy(), xgBoostCreateDto.lastTimestampTrainSell(), OffsetDateTime.now(ZoneId.systemDefault()));
  }
}
