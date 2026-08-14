package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.core.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Service
public class CandlestickService {

  private final CandlestickRepository repository;
  private final CandlestickMapper mapper;

  public Mono<CandlestickDto> get(SymbolDto symbolDto, Timeframe timeframe, OffsetDateTime timestamp) {
    log.debug("Get [symbolDto={}, timeframe={}, timestamp={}]", symbolDto, timeframe, timestamp);
    return this.getRepository().findBySymbolNameAndTimeframeAndTimestamp(symbolDto.name(), timeframe, timestamp).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
  }

  public Flux<CandlestickDto> get(SymbolDto symbolDto, Timeframe timeframe) {
    log.debug("Get [symbolDto={}, timeframe={}]", symbolDto, timeframe);
    return this.getRepository().findAllBySymbolNameAndTimeframeOrderByTimestampAsc(symbolDto.name(), timeframe).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
  }

  public Flux<CandlestickDto> get(SymbolDto symbolDto, Timeframe timeframe, OffsetDateTime startTime, OffsetDateTime endTime) {
    log.debug("Get [symbolDto={}, timeframe={}, startTime={}, endTime={}]", symbolDto, timeframe, startTime, endTime);
    return this.getRepository().findAllBySymbolNameAndTimeframeAndTimestampBetweenOrderByTimestampAsc(symbolDto.name(), timeframe, startTime, endTime).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
  }

  public Flux<CandlestickDto> getPreviousIndicators(SymbolDto symbolDto, Timeframe timeframe, OffsetDateTime timestamp, int size) {
    return this.getRepository().findAllBySymbolNameAndTimeframeAndTimestampLessThanEqualOrderByTimestampDesc(symbolDto.name(), timeframe, timestamp, PageRequest.of(0, size))
        .collectList()
        .flatMapMany(list -> {
          list.sort(Comparator.comparing(Candlestick::timestamp));
          return Flux.fromIterable(list);
        }).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
  }

  public Mono<CandlestickDto> create(SymbolDto symbolDto, Timeframe timeframe, CandlestickCreateDto candlestickCreateDto) {
    log.debug("Get [symbolDto={}, timeframe={}, candlestickCreateDto={}]", symbolDto, timeframe, candlestickCreateDto);
    return this.get(symbolDto, timeframe, candlestickCreateDto.timestamp()).flatMap(candlestickDto -> {
      log.trace("Create [symbolDto={}, timeframe={}, candlestickCreateDto={}], object already exist [candlestickDto={}]", symbolDto, timeframe, candlestickCreateDto, candlestickDto);
      Candlestick model = this.getMapper().toModel(candlestickDto.id(), symbolDto, timeframe, candlestickCreateDto);
      log.trace("Create [symbolDto={}, timeframe={}, candlestickCreateDto={}], updating already exist [candlestick={}]", symbolDto, timeframe, candlestickCreateDto, model);
      return this.getRepository().save(model).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
    }).switchIfEmpty(Mono.defer(() -> {
      Candlestick model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, timeframe, candlestickCreateDto);
      log.trace("Create [symbolDto={}, timeframe={}, candlestickCreateDto={}}], new object generated to save [candlestick={}]", symbolDto, timeframe, candlestickCreateDto, model);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(symbolDto, tick));
    }));
  }

  public Mono<ScopeInputCreateDto> getScope(SymbolDto symbolDto, Timeframe timeframe) {
    log.debug("Get scope [symbolDto={}]", symbolDto);
    return Mono.zip(this.getRepository().findFirstBySymbolNameAndTimeframeOrderByTimestampAsc(symbolDto.name(), timeframe), this.getRepository().findFirstBySymbolNameAndTimeframeOrderByTimestampDesc(symbolDto.name(), timeframe))
        .flatMap(objects -> Mono.just(new ScopeInputCreateDto(objects.getT1().timestamp(), objects.getT2().timestamp())));
  }

}
