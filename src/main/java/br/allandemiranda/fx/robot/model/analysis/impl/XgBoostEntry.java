package br.allandemiranda.fx.robot.model.analysis.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.analysis.XgBoost;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("analysis_xgboost")
public record XgBoostEntry(
    @Id @Column("id") UUID id,
    @Column("symbol_name") String symbolName,
    @Column("timeframe") Timeframe timeframe,
    @Column("ea_name") String eaName,
    @Column("version") String version,
    @Column("model_data_buy") byte[] modelDataBuy,
    @Column("model_data_sell") byte[] modelDataSell,
    @Column("data_set_size_buy") long dataSetSizeBuy,
    @Column("data_set_size_sell") long dataSetSizeSell,
    @Column("last_timestamp_buy") OffsetDateTime lastTimestampTrainBuy,
    @Column("last_timestamp_sell") OffsetDateTime lastTimestampTrainSell,
    @Column("create_at") OffsetDateTime createdAt
) implements XgBoost {

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XgBoostEntry that = (XgBoostEntry) o;
    return dataSetSizeBuy == that.dataSetSizeBuy && dataSetSizeSell == that.dataSetSizeSell && Objects.equals(id, that.id) && Objects.equals(eaName, that.eaName) && Objects.equals(version, that.version)
        && Objects.equals(symbolName, that.symbolName) && timeframe == that.timeframe && Objects.equals(createdAt, that.createdAt) && Objects.equals(lastTimestampTrainBuy, that.lastTimestampTrainBuy)
        && Objects.equals(lastTimestampTrainSell, that.lastTimestampTrainSell);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, symbolName, timeframe, eaName, version, dataSetSizeBuy, dataSetSizeSell, lastTimestampTrainBuy, lastTimestampTrainSell, createdAt);
  }

  @Override
  public String toString() {
    return "XgBoostEntry{" +
        "id=" + id +
        ", symbolName='" + symbolName + '\'' +
        ", timeframe=" + timeframe +
        ", eaName='" + eaName + '\'' +
        ", version='" + version + '\'' +
        ", dataSetSizeBuy=" + dataSetSizeBuy +
        ", dataSetSizeSell=" + dataSetSizeSell +
        ", lastTimestampTrainBuy=" + lastTimestampTrainBuy +
        ", lastTimestampTrainSell=" + lastTimestampTrainSell +
        ", createdAt=" + createdAt +
        '}';
  }
}
