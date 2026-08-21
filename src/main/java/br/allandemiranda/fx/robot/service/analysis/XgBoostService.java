package br.allandemiranda.fx.robot.service.analysis;

import br.allandemiranda.fx.robot.controller.util.VersionUtils;
import br.allandemiranda.fx.robot.dto.analysis.XgBoostDto;
import br.allandemiranda.fx.robot.dto.analysis.create.XgBoostCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.analysis.XgBoostMapper;
import br.allandemiranda.fx.robot.model.analysis.impl.XgBoostEntry;
import br.allandemiranda.fx.robot.repository.analysis.XgBoostRepository;
import java.util.Comparator;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Service
public class XgBoostService {

  private static final String LATEST_VERSION = "latest";
  private final XgBoostMapper mapper;
  private final XgBoostRepository repository;

  public Mono<XgBoostDto> create(String symbolName, Timeframe timeframe, String eaName, XgBoostCreateDto xgBoostCreateDto) {
    log.debug("Create an XgBoost [symbolName={}, timeframe={}, eaName={}, version={}]", symbolName, timeframe, eaName, xgBoostCreateDto.version());
    return this.get(symbolName, timeframe, eaName, xgBoostCreateDto.version()).flatMap(xgBoostDto -> {
      String nextPatchVersion = VersionUtils.getNextPatchVersion(xgBoostCreateDto.version());
      log.debug("The version {} for [symbolName={}, timeframe={}, eaName={}] alredy exist, updating version to {}", xgBoostCreateDto.version(), symbolName, timeframe, eaName, nextPatchVersion);
      XgBoostCreateDto updateVersion = new XgBoostCreateDto(nextPatchVersion, xgBoostCreateDto.modelDataBuy(), xgBoostCreateDto.modelDataSell(), xgBoostCreateDto.dataSetSizeBuy(), xgBoostCreateDto.dataSetSizeSell(),
          xgBoostCreateDto.lastTimestampTrainBuy(), xgBoostCreateDto.lastTimestampTrainSell());
      XgBoostEntry model = this.getMapper().toModel(UUID.randomUUID(), symbolName, timeframe, eaName, updateVersion);
      return this.getRepository().save(model).map(xgBoost -> this.getMapper().toDto(xgBoost));
    }).switchIfEmpty(Mono.defer(() -> {
      XgBoostEntry model = this.getMapper().toModel(UUID.randomUUID(), symbolName, timeframe, eaName, xgBoostCreateDto);
      return this.getRepository().save(model).map(xgBoost -> this.getMapper().toDto(xgBoost));
    }));
  }

  public Mono<XgBoostDto> get(String symbolName, Timeframe timeframe, String eaName, String version) {
    log.debug("Get an XgBoost by [symbolName={}, timeframe={}, eaName={}, version={}]", symbolName, timeframe, eaName, version);
    if (LATEST_VERSION.equals(version)) {
      return this.get(symbolName, timeframe, eaName).sort(Comparator.comparing(XgBoostDto::version, VersionUtils.comparator().reversed())).next();
    } else {
      return this.getRepository().findBySymbolNameAndTimeframeAndEaNameAndVersion(symbolName, timeframe, eaName, version).map(xgBoost -> this.getMapper().toDto(xgBoost));
    }
  }

  public Flux<XgBoostDto> get(String symbolName, Timeframe timeframe, String eaName) {
    return this.getRepository().findBySymbolNameAndTimeframeAndEaName(symbolName, timeframe, eaName).map(xgBoost -> this.getMapper().toDto(xgBoost));
  }
}
