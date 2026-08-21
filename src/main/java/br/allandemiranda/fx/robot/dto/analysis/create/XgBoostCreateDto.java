package br.allandemiranda.fx.robot.dto.analysis.create;

import br.allandemiranda.fx.robot.model.analysis.XgBoostDataSet;
import br.allandemiranda.fx.robot.model.analysis.XgBoostModelData;
import br.allandemiranda.fx.robot.model.analysis.XgBoostVersion;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public record XgBoostCreateDto(
    String version,
    byte[] modelDataBuy,
    byte[] modelDataSell,
    long dataSetSizeBuy,
    long dataSetSizeSell,
    OffsetDateTime lastTimestampTrainBuy,
    OffsetDateTime lastTimestampTrainSell
) implements Serializable, XgBoostVersion, XgBoostDataSet, XgBoostModelData {

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XgBoostCreateDto createDto = (XgBoostCreateDto) o;
    return dataSetSizeBuy == createDto.dataSetSizeBuy && dataSetSizeSell == createDto.dataSetSizeSell && Objects.equals(version, createDto.version) && Objects.equals(lastTimestampTrainBuy,
        createDto.lastTimestampTrainBuy) && Objects.equals(lastTimestampTrainSell, createDto.lastTimestampTrainSell);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, dataSetSizeBuy, dataSetSizeSell, lastTimestampTrainBuy, lastTimestampTrainSell);
  }

  @Override
  public @NotNull String toString() {
    return "XgBoostCreateDto{" +
        "version='" + version + '\'' +
        ", dataSetSizeBuy=" + dataSetSizeBuy +
        ", dataSetSizeSell=" + dataSetSizeSell +
        ", lastTimestampTrainBuy=" + lastTimestampTrainBuy +
        ", lastTimestampTrainSell=" + lastTimestampTrainSell +
        '}';
  }
}
