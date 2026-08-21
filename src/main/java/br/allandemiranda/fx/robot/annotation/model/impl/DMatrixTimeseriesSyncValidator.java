package br.allandemiranda.fx.robot.annotation.model.impl;

import br.allandemiranda.fx.robot.annotation.model.DMatrixRowIndicatorsValidate;
import br.allandemiranda.fx.robot.dto.provider.DMatrixIndicatorsRow;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.OffsetDateTime;
import java.util.List;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class DMatrixTimeseriesSyncValidator implements ConstraintValidator<DMatrixRowIndicatorsValidate, DMatrixIndicatorsRow> {

  @Override
  public boolean isValid(DMatrixIndicatorsRow dto, ConstraintValidatorContext context) {

    List<? extends Timeseries>[] seriesLists = (List<? extends Timeseries>[]) new List<?>[]{
        dto.adxs(),
        dto.atrs(),
        dto.bandss(),
        dto.macds(),
        dto.maFasts(),
        dto.maSlows(),
        dto.rsis(),
        dto.stochastics()
    };

    int expectedSize = seriesLists[0].size();

    for (int i = 1; i < seriesLists.length; i++) {
      if (seriesLists[i].size() != expectedSize) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Time series lists are not all the same size").addConstraintViolation();
        return false;
      }
    }

    for (int i = 0; i < expectedSize - 1; i++) {
      Timeseries currentItem = seriesLists[0].get(i);
      Timeseries nextItem = seriesLists[0].get(i + 1);

      if (nextItem.timestamp().isBefore(currentItem.timestamp())) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Time series lists must be chronologically ordered (oldest to newest). Inversion found at index " + i).addConstraintViolation();
        return false;
      }
    }

    for (int i = 0; i < expectedSize; i++) {
      Timeseries referenceItem = seriesLists[0].get(i);
      OffsetDateTime referenceTimestamp = referenceItem.timestamp();

      for (int listIdx = 1; listIdx < seriesLists.length; listIdx++) {
        Timeseries currentItem = seriesLists[listIdx].get(i);
        OffsetDateTime currentTimestamp = currentItem.timestamp();

        if (!referenceTimestamp.isEqual(currentTimestamp)) {
          context.disableDefaultConstraintViolation();
          context.buildConstraintViolationWithTemplate("Timestamp discrepancy found in index " + i + ": expected " + referenceTimestamp + " but found " + currentTimestamp).addConstraintViolation();
          return false;
        }
      }
    }

    return true;
  }
}