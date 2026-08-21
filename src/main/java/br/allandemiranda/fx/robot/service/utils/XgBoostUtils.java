package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.model.input.provider.XGBoostInputParameters;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

@NullMarked
@Slf4j
@UtilityClass
public class XgBoostUtils {

  private static double calculateIncrementalEta(double baseEta, long oldDataCount, long newDataCount) {
    if (oldDataCount <= 0 || newDataCount <= 0) {
      return baseEta;
    }

    double ratio = (double) newDataCount / oldDataCount;
    double scaleFactor = Math.sqrt(ratio);
    double clampedFactor = Math.clamp(scaleFactor, 0.2, 1.0);
    return baseEta * clampedFactor;
  }

  @Contract(pure = true)
  private static @Unmodifiable List<Integer> getAvailableGpuIds() {
    ProcessBuilder processBuilder = new ProcessBuilder("nvidia-smi", "--query-gpu=index", "--format=csv,noheader,nounits");

    try {
      Process process = processBuilder.start();

      try (BufferedReader reader = process.inputReader()) {
        List<Integer> gpuIds = reader.lines().map(String::trim).filter(line -> !line.isEmpty()).map(Integer::parseInt).toList();
        int exitCode = process.waitFor();

        if (exitCode != 0) {
          log.warn("nvidia-smi exited with code {}", exitCode);
          return List.of();
        }
        gpuIds.forEach(gpuId -> log.trace("GPU ID available: {}", gpuId));
        return gpuIds;
      }
    } catch (IOException e) {
      log.warn("NVIDIA SMI unavailable or driver not installed: {}", e.getMessage());
      return List.of();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.warn("Interrupted while waiting for nvidia-smi: {}", e.getMessage());
      return List.of();
    }
  }

  @Contract(pure = true)
  private static @Unmodifiable Map<String, Object> getDefaultParams(XGBoostInputParameters inputParams, boolean useGpu, @Range(from = 0, to = Integer.MAX_VALUE) int gpuId) {
    Map<String, Object> p = new HashMap<>();
    p.put("objective", "binary:logistic");
    p.put("eval_metric", "logloss");

    // Model complexity
    p.put("max_depth", inputParams.maxDepth());
    p.put("min_child_weight", inputParams.minChildWeight());

    // Learning
    p.put("eta", inputParams.eta());

    // Sampling
    p.put("subsample", inputParams.subsample());
    p.put("colsample_bytree", inputParams.colSampleByTree());

    // Regularization
    p.put("lambda", inputParams.lambda());  // L2
    p.put("alpha", inputParams.alpha());    // L1

    // CPU vs GPU
    List<Integer> availableGpus = XgBoostUtils.getAvailableGpuIds();
    boolean shouldRunOnGpu = useGpu && !availableGpus.isEmpty();

    if (shouldRunOnGpu) {
      int selectedGpuId = availableGpus.contains(gpuId) ? gpuId : availableGpus.getFirst();

      log.info("Configuring XGBoost to run on GPU ID: {}", selectedGpuId);

      p.put("tree_method", "hist");
      p.put("device", "cuda:" + selectedGpuId);
    } else {
      if (useGpu) {
        log.warn("GPU requested, but no compatible NVIDIA GPU was found. Using CPU.");
      }
      p.put("tree_method", "hist");
      p.put("device", "cpu");
      p.put("nthread", Runtime.getRuntime().availableProcessors());
    }

    return p;
  }

  @SneakyThrows
  public static byte[] boosterToBytes(Booster booster) {
    return booster.toByteArray();
  }

  @SneakyThrows
  public static Booster bytesToBooster(byte[] modelData) {
    try (InputStream inputStream = new ByteArrayInputStream(modelData)) {
      return XGBoost.loadModel(inputStream);
    }
  }

  @Contract(pure = true)
  @SneakyThrows
  public static float[][] runPredicateSimple(float[] x, Booster booster) {
    DMatrix d = new DMatrix(x, 1, x.length, Float.NaN);
    return booster.predict(d);
  }

  @Contract(pure = true)
  @SneakyThrows
  public static Booster trainAndValidation(DMatrix train, DMatrix validation, XGBoostInputParameters xgBoostInputParameters, boolean useGpu, @Range(from = 0, to = Integer.MAX_VALUE) int gpuId) {
    Map<String, Object> params = XgBoostUtils.getDefaultParams(xgBoostInputParameters, useGpu, gpuId);

    Map<String, DMatrix> watch = new HashMap<>();
    watch.put("train", train);
    watch.put("validation", validation);

    return XGBoost.train(train, params, xgBoostInputParameters.rounds(), watch, null, null, null, xgBoostInputParameters.earlyStoppingRounds());
  }

  @Contract(pure = true)
  @SneakyThrows
  @SuppressWarnings("unused")
  public static Booster trainAndValidation(DMatrix train, DMatrix validation, XGBoostInputParameters xgBoostInputParameters, @Range(from = 0, to = Long.MAX_VALUE) long oldTrainSize, Booster oldBooster, boolean useGpu,
      @Range(from = 0, to = Integer.MAX_VALUE) int gpuId) {
    HashMap<String, Object> params = new HashMap<>(XgBoostUtils.getDefaultParams(xgBoostInputParameters, useGpu, gpuId));
    params.put("eta", XgBoostUtils.calculateIncrementalEta(xgBoostInputParameters.eta(), oldTrainSize, train.rowNum()));

    Map<String, DMatrix> watch = new HashMap<>();
    watch.put("new_data", train);
    watch.put("validation", validation);

    return XGBoost.train(train, params, xgBoostInputParameters.rounds(), watch, null, null, null, xgBoostInputParameters.earlyStoppingRounds(), oldBooster);
  }

}
