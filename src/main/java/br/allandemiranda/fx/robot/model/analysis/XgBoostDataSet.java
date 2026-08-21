package br.allandemiranda.fx.robot.model.analysis;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import java.time.OffsetDateTime;

public interface XgBoostDataSet {

  @Positive
  long dataSetSizeBuy();

  @Positive
  long dataSetSizeSell();

  @NotNull
  @PastOrPresent
  OffsetDateTime lastTimestampTrainBuy();

  @NotNull
  @PastOrPresent
  OffsetDateTime lastTimestampTrainSell();

}
