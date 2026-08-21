package br.allandemiranda.fx.robot.service.analysis;

import br.allandemiranda.fx.robot.repository.analysis.XgBoostRepository;
import br.allandemiranda.fx.robot.service.utils.XgBoostUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import ml.dmlc.xgboost4j.java.Booster;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Service
public class BoosterService {

  private static final Map<Integer, Booster[]> CACHE_REPOSITORY = new HashMap<Integer, Booster[]>();
  private final XgBoostRepository xgBoostRepository;

  private static int getCacheRepositoryKey(String eaName, String version) {
    return Objects.hash(eaName, version);
  }

  @Synchronized
  private static Mono<Booster[]> setCacheBooster(String eaName, String version, Booster[] boosters) {
    return Mono.justOrEmpty(BoosterService.CACHE_REPOSITORY.put(BoosterService.getCacheRepositoryKey(eaName, version), boosters)).thenReturn(boosters);
  }

  public Mono<Booster[]> getBoosters(String eaName, String version) {
    return Mono.justOrEmpty(BoosterService.CACHE_REPOSITORY.get(BoosterService.getCacheRepositoryKey(eaName, version)))
        .switchIfEmpty(this.getXgBoostRepository().findByEaNameAndVersion(eaName, version)
            .map(xgBoostEntry -> new Booster[]{XgBoostUtils.bytesToBooster(xgBoostEntry.modelDataBuy()), XgBoostUtils.bytesToBooster(xgBoostEntry.modelDataSell())})
            .flatMap(boosters -> BoosterService.setCacheBooster(eaName, version, boosters)));
  }

  public Mono<Booster> getBuyBooster(String eaName, String version) {
    return this.getBoosters(eaName, version).map(boosters -> boosters[0]);
  }

  public Mono<Booster> getSellBooster(String eaName, String version) {
    return this.getBoosters(eaName, version).map(boosters -> boosters[1]);
  }


}
