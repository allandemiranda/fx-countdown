package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import br.allandemiranda.fx.robot.dto.provider.DMatrixRow;
import br.allandemiranda.fx.robot.dto.provider.DMatrixTrainRow;
import br.allandemiranda.fx.robot.dto.provider.Garch;
import br.allandemiranda.fx.robot.dto.provider.GarchForecast;
import br.allandemiranda.fx.robot.dto.provider.PriceRiskLevel;
import br.allandemiranda.fx.robot.dto.provider.Trading;
import br.allandemiranda.fx.robot.enums.DealReason;
import br.allandemiranda.fx.robot.enums.OrderType;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.enums.XgBoostLabel;
import br.allandemiranda.fx.robot.enums.XgBoostWatch;
import br.allandemiranda.fx.robot.model.core.SymbolParameters;
import br.allandemiranda.fx.robot.model.core.Tick;
import br.allandemiranda.fx.robot.model.indicator.ADX;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import br.allandemiranda.fx.robot.model.indicator.MACD;
import br.allandemiranda.fx.robot.model.indicator.MaFast;
import br.allandemiranda.fx.robot.model.indicator.MaSlow;
import br.allandemiranda.fx.robot.model.indicator.RSI;
import br.allandemiranda.fx.robot.model.indicator.Stochastic;
import br.allandemiranda.fx.robot.model.input.GarchInput;
import br.allandemiranda.fx.robot.model.input.PriceRiskLevelInput;
import br.allandemiranda.fx.robot.model.input.XgBoostInput;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import java.io.BufferedWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.DMatrix;
import org.apache.commons.lang3.tuple.Triple;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class DMatrixUtils {

  private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

  private static <T> void appendListFeatures(List<Float> target, @Unmodifiable List<T> list, Function<T, @Unmodifiable List<Float>> extractor) {
    for (T obj : list) {
      target.addAll(extractor.apply(obj));
    }
  }

  @Contract(pure = true)
  private static int calculateSplitIndex(@Unmodifiable List<?> lines, BigDecimal validationPercentage) {
    if (validationPercentage.compareTo(BigDecimal.ZERO) <= 0) {
      return lines.size();
    }

    if (validationPercentage.compareTo(BigDecimal.ONE) >= 0) {
      return 0;
    }

    int validationCount = BigDecimal.valueOf(lines.size()).multiply(validationPercentage).setScale(0, DEFAULT_ROUNDING_MODE).intValue();
    return lines.size() - validationCount;
  }

  private static Function<OrderType, Stream<? extends GarchForecast>> generateGarchForecasts(GarchInput garchInput, Timeframe timeframe, @Unmodifiable List<? extends Tick> ticks, int horizon) {
    return orderType -> {
      //generate the candlesticks by ticks
      List<Candlestick> candlesticks = CandlestickUtils.getCandlesticksByTicks(ticks, timeframe, orderType);
      //generate the garch forecasts
      return GarchUtils.getGarchForecasts(garchInput, orderType).apply(candlesticks).skip(horizon + 1);
    };
  }

  private static Function<GarchForecast, Entry<OrderType, DMatrixTrainRow>> generateRows(PriceRiskLevelInput priceRiskLevelInput, Timeframe timeframe, @Unmodifiable List<? extends Tick> ticks, SymbolParameters symbolParameters,
      @Unmodifiable List<? extends ADX> adxs, @Unmodifiable List<? extends ATR> atrs, @Unmodifiable List<? extends Bands> bandss, @Unmodifiable List<? extends MaFast> maFasts, @Unmodifiable List<? extends MaSlow> maSlows,
      @Unmodifiable List<? extends MACD> macds, @Unmodifiable List<? extends RSI> rsis,
      @Unmodifiable List<? extends Stochastic> stochastics, int horizon) {
    return garchForecast -> {
      OffsetDateTime timestamp = garchForecast.timestamp();
      OrderType orderType = garchForecast.orderType();

      //generate the price risk level by garch forecast
      PriceRiskLevel priceRiskLevel = PriceRiskLevelUtils.getPriceRiskLevelByGarchForecast(priceRiskLevelInput, symbolParameters).apply(garchForecast);
      //get max timestamp tick to avoid use memory to unnecessary tick
      OffsetDateTime maxRolloverCutoff = TradingUtils.getMaxRolloverCutoff(timestamp, garchForecast.price(), priceRiskLevel.tpPrice(), symbolParameters, TradingUtils.getPositionType(orderType));
      //generate the trading result
      Trading trading = TradingUtils.getTradingResult(priceRiskLevel, symbolParameters).apply(TradingUtils.getMaxRolloverCutoffTicks(ticks, timestamp, maxRolloverCutoff));

      //generate the new row on DMatrix data set
      DMatrixTrainRow row = new DMatrixTrainRow() {
        @Override
        public List<? extends ADX> adxs() {
          return TimeseriesUtils.getTimeseriesWindow(adxs, timestamp, horizon);
        }

        @Override
        public List<? extends ATR> atrs() {
          return TimeseriesUtils.getTimeseriesWindow(atrs, timestamp, horizon);
        }

        @Override
        public List<? extends Bands> bandss() {
          return TimeseriesUtils.getTimeseriesWindow(bandss, timestamp, horizon);
        }

        @Override
        public List<? extends Candlestick> candlesticks() {
          return CandlestickUtils.getCandlesticksByTicks(ticks, timeframe, orderType, timestamp, horizon);
        }

        @Override
        public Garch garch() {
          return garchForecast;
        }

        @Override
        public XgBoostLabel label() {
          return DealReason.DEAL_REASON_TP.equals(trading.dealReason()) ? XgBoostLabel.OPEN : XgBoostLabel.NOT_OPEN;
        }

        @Override
        public List<? extends MaFast> maFasts() {
          return TimeseriesUtils.getTimeseriesWindow(maFasts, timestamp, horizon);
        }

        @Override
        public List<? extends MaSlow> maSlows() {
          return TimeseriesUtils.getTimeseriesWindow(maSlows, timestamp, horizon);
        }

        @Override
        public List<? extends MACD> macds() {
          return TimeseriesUtils.getTimeseriesWindow(macds, timestamp, horizon);
        }

        @Override
        public List<? extends RSI> rsis() {
          return TimeseriesUtils.getTimeseriesWindow(rsis, timestamp, horizon);
        }

        @Override
        public List<? extends Stochastic> stochastics() {
          return TimeseriesUtils.getTimeseriesWindow(stochastics, timestamp, horizon);
        }

        @Override
        public OffsetDateTime timestamp() {
          return timestamp;
        }
      };

      return Map.entry(orderType, row);
    };
  }

  @Contract(pure = true)
  private <D extends ADX> Function<D, @Unmodifiable List<Float>> getADX() {
    return adx -> {
      float mainLine = adx.mainLine().floatValue();
      float plusDiLine = adx.plusDiLine().floatValue();
      float minusDiLine = adx.minusDiLine().floatValue();

      return List.of(mainLine, plusDiLine, minusDiLine);
    };
  }

  @Contract(pure = true)
  private <D extends ATR> Function<D, @Unmodifiable List<Float>> getATR() {
    return atr -> List.of(atr.atr().floatValue());
  }

  @Contract(pure = true)
  private <D extends Bands> Function<D, @Unmodifiable List<Float>> getBands() {
    return bands -> {
      float baseLine = bands.baseLine().floatValue();
      float upperBand = bands.upperBand().floatValue();
      float lowerBand = bands.lowerBand().floatValue();

      return List.of(baseLine, upperBand, lowerBand);
    };
  }

  @Contract(pure = true)
  private <D extends Candlestick> Function<D, @Unmodifiable List<Float>> getCandlestick() {
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

  @Contract(pure = true)
  private <D extends Garch> Function<D, @Unmodifiable List<Float>> getGarch() {
    return garchForecastDto -> {
      float omega = (float) garchForecastDto.omega();
      float alpha = (float) garchForecastDto.alpha();
      float beta = (float) garchForecastDto.beta();
      float sigmaAgg = (float) garchForecastDto.sigmaAgg();

      return List.of(omega, alpha, beta, sigmaAgg);
    };
  }

  @Contract(pure = true)
  private <D extends MACD> Function<D, @Unmodifiable List<Float>> getMACD() {
    return macd -> {
      float mainLine = macd.mainLine().floatValue();
      float signalLine = macd.signalLine().floatValue();

      return List.of(mainLine, signalLine);
    };
  }

  @Contract(pure = true)
  private <D extends MaFast> Function<D, @Unmodifiable List<Float>> getMaFast() {
    return maFast -> List.of(maFast.ma().floatValue());
  }

  @Contract(pure = true)
  private <D extends MaSlow> Function<D, @Unmodifiable List<Float>> getMaSlow() {
    return mSlow -> List.of(mSlow.ma().floatValue());
  }

  @Contract(pure = true)
  private <D extends RSI> Function<D, @Unmodifiable List<Float>> getRSI() {
    return rsi -> List.of(rsi.rsi().floatValue());
  }

  @Contract(pure = true)
  private <D extends Stochastic> Function<D, @Unmodifiable List<Float>> getStochastic() {
    return stochastic -> {
      float mainLine = stochastic.mainLine().floatValue();
      float signalLine = stochastic.signalLine().floatValue();

      return List.of(mainLine, signalLine);
    };
  }

  @Contract(pure = true)
  private static String toLibSvmRow(DMatrixTrainRow dMatrixTrainRow) {
    StringBuilder builder = new StringBuilder();

    int label = dMatrixTrainRow.label().getValue();
    builder.append(label);

    List<Float> features = DMatrixUtils.extractAllFeatures(dMatrixTrainRow);

    for (int i = 0; i < features.size(); i++) {
      Float val = features.get(i);
      builder.append(" ").append(i + 1).append(":").append(val);
    }

    return builder.toString();
  }

  @Contract(pure = true)
  public static @Unmodifiable List<Float> extractAllFeatures(DMatrixRow dMatrixRow) {
    List<Float> features = new ArrayList<>();

    DMatrixUtils.appendListFeatures(features, List.of(dMatrixRow.garch()), DMatrixUtils.getGarch());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.candlesticks(), DMatrixUtils.getCandlestick());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.adxs(), DMatrixUtils.getADX());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.atrs(), DMatrixUtils.getATR());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.bandss(), DMatrixUtils.getBands());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.macds(), DMatrixUtils.getMACD());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.maFasts(), DMatrixUtils.getMaFast());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.maSlows(), DMatrixUtils.getMaSlow());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.rsis(), DMatrixUtils.getRSI());
    DMatrixUtils.appendListFeatures(features, dMatrixRow.stochastics(), DMatrixUtils.getStochastic());

    return features;
  }

  public static @Unmodifiable Map<Object, @Unmodifiable Map<Object, @Unmodifiable Object[]>> generateDataSetFile(GarchInput garchInput, PriceRiskLevelInput priceRiskLevelInput, XgBoostInput xgBoostInput, Timeframe timeframe,
      @Unmodifiable List<? extends Tick> ticks, SymbolParameters symbolParameters, @Unmodifiable List<? extends ADX> adxs, @Unmodifiable List<? extends ATR> atrs, @Unmodifiable List<? extends Bands> bandss,
      @Unmodifiable List<? extends MaFast> maFasts, @Unmodifiable List<? extends MaSlow> maSlows, @Unmodifiable List<? extends MACD> macds, @Unmodifiable List<? extends RSI> rsis,
      @Unmodifiable List<? extends Stochastic> stochastics) {
    //horizon used to aggregate data on XgBoost analysis
    int horizon = xgBoostInput.horizon();

    //define orders type branch
    return StreamSupport.stream(Arrays.stream(OrderType.values()).spliterator(), true)
        .flatMap(DMatrixUtils.generateGarchForecasts(garchInput, timeframe, ticks, horizon))
        .map(DMatrixUtils.generateRows(priceRiskLevelInput, timeframe, ticks, symbolParameters, adxs, atrs, bandss, maFasts, maSlows, macds, rsis, stochastics, horizon))
        .collect(Collectors.groupingBy(Entry::getKey, Collectors.collectingAndThen(Collectors.toList(), list -> list.stream().map(Entry::getValue).sorted(Comparator.comparing(Timeseries::timestamp)).toList())))
        .entrySet().parallelStream().flatMap(entry -> {
          OrderType orderType = entry.getKey();
          List<DMatrixTrainRow> rows = entry.getValue();
          return Stream.of(Triple.of(orderType, XgBoostWatch.TRAIN, DMatrixUtils.getTrainDataset(rows, xgBoostInput.validationPercentage())),
              Triple.of(orderType, XgBoostWatch.VALIDATION, DMatrixUtils.getValidationDataset(rows, xgBoostInput.validationPercentage())));
        })
        .map(triple -> {
          OrderType orderType = triple.getLeft();
          XgBoostWatch xgBoostWatch = triple.getMiddle();
          List<DMatrixTrainRow> rows = triple.getRight();
          OffsetDateTime lastTimestamp = rows.stream().map(Timeseries::timestamp).sorted().findFirst().orElseThrow(IllegalStateException::new);

          Path libSvmFile = DMatrixUtils.writeLibSvmFile(rows, UUID.randomUUID() + "_" + orderType.getValue() + "_" + xgBoostWatch.getValue() + ".libsvm");

          return new Object[]{orderType, xgBoostWatch, libSvmFile, rows.size(), lastTimestamp};
        }).collect(Collectors.groupingBy(o -> o[0], Collectors.toMap(o -> o[1], o -> new Object[]{o[2], o[3], o[4]})));
  }

  @Contract(pure = true)
  public static @Unmodifiable <T> List<T> getTrainDataset(@Unmodifiable List<T> lines, BigDecimal validationPercentage) {
    int splitIdx = DMatrixUtils.calculateSplitIndex(lines, validationPercentage);
    return lines.subList(0, splitIdx);
  }

  @Contract(pure = true)
  public static @Unmodifiable <T> List<T> getValidationDataset(@Unmodifiable List<T> lines, BigDecimal validationPercentage) {
    int splitIdx = DMatrixUtils.calculateSplitIndex(lines, validationPercentage);
    return lines.subList(splitIdx, lines.size());
  }

  @SneakyThrows
  public static DMatrix readDataSet(Path file) {
    return new DMatrix(file.toString());
  }

  @SneakyThrows
  public static Path writeLibSvmFile(@Unmodifiable List<DMatrixTrainRow> rows, String fileName) {
    Path tempPath = Paths.get(System.getProperty("java.io.tmpdir"));
    Path targetFile = tempPath.resolve(fileName);

    try (BufferedWriter writer = Files.newBufferedWriter(targetFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
      for (DMatrixTrainRow row : rows) {
        writer.write(toLibSvmRow(row));
        writer.newLine();
      }
    }

    return targetFile;
  }
}
