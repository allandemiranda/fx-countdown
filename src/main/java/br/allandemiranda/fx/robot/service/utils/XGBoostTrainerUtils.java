package br.allandemiranda.fx.robot.service.utils;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

@Slf4j
@UtilityClass
public class XGBoostTrainerUtils {

  /**
   * Treina em 100% dos dados
   *
   * @param rounds número de boosting rounds (ex.: 300)
   * @return Booster treinado (você pode reutilizar para prever em memória)
   */
  public static Booster trainAll(Map<String, Object> params, DMatrix train, int rounds, Path saveFile) throws XGBoostError {
    Map<String, DMatrix> watch = Map.of("train", train);
    Booster booster = XGBoost.train(train, params, rounds, watch, null, null);
    booster.saveModel(saveFile.toString());
    return booster;
  }

  // * @param numClasses número de classes (ex.: 3 para BUY/SELL/NEUTRAL, 2 para OPEN, NOT_OPEN)
  //     * @param useGpu     true para usar GPU (tree_method=gpu_hist)
  //     * @param gpuId      id da GPU CUDA (geralmente 0 se há 1 GPU NVIDIA)
  public static Map<String, Object> getDefaultParams(int numClasses, int maxDepth, float eta, float subsample, float colSampleByTree,int minChildWeight, float lambda, float alpha, boolean useGpu, int gpuId, Integer seed) {
    Map<String, Object> p = new HashMap<>();
    p.put("objective", "multi:softprob");
    p.put("num_class", numClasses);
    p.put("eval_metric", "mlogloss");

    // Model complexity
    p.put("max_depth", maxDepth);
    p.put("min_child_weight", minChildWeight);

    // Learning
    p.put("eta", eta);

    // Sampling
    p.put("subsample", subsample);
    p.put("colsample_bytree", colSampleByTree);

    // Regularization
    p.put("lambda", lambda);  // L2
    p.put("alpha", alpha);    // L1

    if (seed != null) p.put("seed", seed);

    // CPU vs GPU
    if (useGpu) {
      p.put("tree_method", "gpu_hist");
      p.put("predictor", "gpu_predictor");
      p.put("gpu_id", gpuId);
    } else {
      p.put("tree_method", "hist");
      p.put("nthread", Runtime.getRuntime().availableProcessors());
    }

    return p;
  }

  public static Booster loadModel(String path) throws XGBoostError {
    return XGBoost.loadModel(path);
  }

  @SneakyThrows
  public static float[][] runPredicateSimple(float[] x, Booster booster) {
    // Cria DMatrix com 1 linha e p colunas
    DMatrix d = new DMatrix(x, 1, x.length, Float.NaN);

    // Probabilidades por classe (multi:softprob)
    return booster.predict(d);
    //result[0][0] = 0.23 -> probabilidade da classe 0
    //result[0][1] = 0.77 -> probabilidade da classe 1
  }
}
