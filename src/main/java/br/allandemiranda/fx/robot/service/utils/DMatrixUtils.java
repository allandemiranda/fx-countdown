package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.DMatrixRowDto;
import br.allandemiranda.fx.robot.dto.analysis.DMatrixPredictRowDto;
import br.allandemiranda.fx.robot.dto.analysis.DMatrixTrainRowDto;
import br.allandemiranda.fx.robot.dto.analysis.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.core.Candlestick;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADX;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATR;
import br.allandemiranda.fx.robot.dto.impl.indicator.Bands;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACD;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFast;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlow;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSI;
import br.allandemiranda.fx.robot.dto.impl.indicator.Stochastic;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoostError;

@Log4j2
@UtilityClass
public class DMatrixUtils {

  private <D extends GarchForecastDto> Function<D, List<Float>> getGarchForecastDto() {
    return garchForecastDto -> {
      float omega = (float) garchForecastDto.omega();
      float alpha = (float) garchForecastDto.alpha();
      float beta = (float) garchForecastDto.beta();
      float sigmaAgg = (float) garchForecastDto.sigmaAgg();

      return List.of(omega, alpha, beta, sigmaAgg);
    };
  }

  private <D extends Candlestick> Function<D, List<Float>> getCandlestick() {
    return candlestick -> {
      float type = 0f; // neutral 0f; bullish 1f; bearish 2f;
      float upperShadow = 0f;
      float lowShadow = 0f;
      float body = 0f;
      int compare = candlestick.open().compareTo(candlestick.close());
      if (compare < 0) {
        type = 1f;
        upperShadow = (candlestick.high().subtract(candlestick.close())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.high().scale())), RoundingMode.HALF_DOWN).floatValue();
        lowShadow = (candlestick.open().subtract(candlestick.low())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.low().scale())), RoundingMode.HALF_DOWN).floatValue();
        body = (candlestick.close().subtract(candlestick.open())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.open().scale())), RoundingMode.HALF_DOWN).floatValue();
      } else if (compare > 0) {
        type = 2f;
        upperShadow = (candlestick.high().subtract(candlestick.open())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.open().scale())), RoundingMode.HALF_DOWN).floatValue();
        lowShadow = (candlestick.close().subtract(candlestick.low())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.low().scale())), RoundingMode.HALF_DOWN).floatValue();
        body = (candlestick.open().subtract(candlestick.close())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.close().scale())), RoundingMode.HALF_DOWN).floatValue();
      } else {
        upperShadow = (candlestick.high().subtract(candlestick.open())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.open().scale())), RoundingMode.HALF_DOWN).floatValue();
        lowShadow = (candlestick.open().subtract(candlestick.low())).divide(BigDecimal.valueOf(Math.pow(10, -candlestick.low().scale())), RoundingMode.HALF_DOWN).floatValue();
      }

      return List.of(type, upperShadow, lowShadow, body);
    };
  }

  private <D extends ADX> Function<D, List<Float>> getADX() {
    return adx -> {
      float mainLine = adx.mainLine().floatValue();
      float plusDiLine = adx.plusDiLine().floatValue();
      float minusDiLine = adx.minusDiLine().floatValue();

      return List.of(mainLine, plusDiLine, minusDiLine);
    };
  }

  private <D extends ATR> Function<D, List<Float>> getATR() {
    return atr -> List.of(atr.atr().floatValue());
  }

  private <D extends Bands> Function<D, List<Float>> getBands() {
    return bands -> {
      float baseLine = bands.baseLine().floatValue();
      float upperBand = bands.upperBand().floatValue();
      float lowerBand = bands.lowerBand().floatValue();

      return List.of(baseLine, upperBand, lowerBand);
    };
  }

  private <D extends MACD> Function<D, List<Float>> getMACD() {
    return macd -> {
      float mainLine = macd.mainLine().floatValue();
      float signalLine = macd.signalLine().floatValue();

      return List.of(mainLine, signalLine);
    };
  }

  private <D extends MaFast> Function<D, List<Float>> getMaFast() {
    return maFast -> List.of(maFast.ma().floatValue());
  }

  private <D extends MaSlow> Function<D, List<Float>> getMaSlow() {
    return mSlow -> List.of(mSlow.ma().floatValue());
  }

  private <D extends RSI> Function<D, List<Float>> getRSI() {
    return rsi -> List.of(rsi.rsi().floatValue());
  }

  private <D extends Stochastic> Function<D, List<Float>> getStochastic() {
    return stochastic -> {
      float mainLine = stochastic.mainLine().floatValue();
      float signalLine = stochastic.signalLine().floatValue();

      return List.of(mainLine, signalLine);
    };
  }

  private static <D extends DMatrixRowDto> List<Float> extractAllFeatures(D dto) {
    List<Float> features = new ArrayList<>();

    DMatrixUtils.appendListFeatures(features, List.of(dto.garchForecastDto()), getGarchForecastDto());
    DMatrixUtils.appendListFeatures(features, dto.candlestickDtos(), getCandlestick());
    DMatrixUtils.appendListFeatures(features, dto.adxDtos(), getADX());
    DMatrixUtils.appendListFeatures(features, dto.atrDtos(), getATR());
    DMatrixUtils.appendListFeatures(features, dto.bandsDtos(), getBands());
    DMatrixUtils.appendListFeatures(features, dto.macdDtos(), getMACD());
    DMatrixUtils.appendListFeatures(features, dto.maFastDtos(), getMaFast());
    DMatrixUtils.appendListFeatures(features, dto.maSlowDtos(), getMaSlow());
    DMatrixUtils.appendListFeatures(features, dto.rsiDtos(), getRSI());
    DMatrixUtils.appendListFeatures(features, dto.stochasticDtos(), getStochastic());

    return features;
  }

  public static float[] getDMatrixPredict(DMatrixPredictRowDto dto) {
    List<Float> features = DMatrixUtils.extractAllFeatures(dto);

    float[] result = new float[features.size()];
    for (int i = 0; i < features.size(); i++) {
      result[i] = features.get(i);
    }

    return result;
  }

  private static <T> void appendListFeatures(List<Float> target, List<T> dtoList, Function<T, List<Float>> extractor) {
    for (T dto : dtoList) {
      if (dto != null) {
        target.addAll(extractor.apply(dto));
      }
    }
  }

  public static String toLibSvmRow(DMatrixTrainRowDto dto) {
    StringBuilder builder = new StringBuilder();

    int label = dto.label().getValue();
    builder.append(label);

    List<Float> features = DMatrixUtils.extractAllFeatures(dto);

    for (int i = 0; i < features.size(); i++) {
      Float val = features.get(i);
      if (val != null && !Float.isNaN(val)) {
        builder.append(" ").append(i).append(":").append(val);
      }
    }

    return builder.toString();
  }

  public static Path saveToLibSvmFile(List<String> lines, Path filePath) throws IOException {
    if (filePath.getParent() != null) {
      Files.createDirectories(filePath.getParent());
    }
    return Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
  }

  public static Path appendToLibSvmFile(String line, Path filePath) throws IOException {
    if (filePath.getParent() != null) {
      Files.createDirectories(filePath.getParent());
    }

    return Files.writeString(
        filePath,
        line + System.lineSeparator(),
        StandardOpenOption.CREATE,
        StandardOpenOption.APPEND,
        StandardOpenOption.WRITE
    );
  }

  public static DMatrix getDMatrix(Path filePath) throws XGBoostError {
    return new DMatrix(filePath.toString());
  }
}
