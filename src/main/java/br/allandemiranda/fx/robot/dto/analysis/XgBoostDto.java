package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.analysis.XgBoostDataSet;
import br.allandemiranda.fx.robot.model.analysis.XgBoostInfo;
import br.allandemiranda.fx.robot.model.provider.Chart;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record XgBoostDto(
    String symbolName,
    Timeframe timeframe,
    String eaName,
    String version,
    long dataSetSizeBuy,
    long dataSetSizeSell,
    OffsetDateTime lastTimestampTrainBuy,
    OffsetDateTime lastTimestampTrainSell,
    OffsetDateTime createdAt
) implements Serializable, XgBoostInfo, XgBoostDataSet, Chart {

}
