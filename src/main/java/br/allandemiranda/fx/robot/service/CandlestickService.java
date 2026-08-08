package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.SymbolDto;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.impl.CandlestickRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
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

  public Mono<CandlestickDto> getOrNext(SymbolDto symbolDto, Timeframe timeframe, OffsetDateTime timestamp) {
    log.debug("GetOrNext [symbolDto={}, timeframe={}, timestamp={}]", symbolDto, timeframe, timestamp);
    return this.get(symbolDto, timeframe, timestamp).switchIfEmpty(Mono.defer(() -> {
      log.trace("GetOrNext [symbolDto={}, timeframe={}, timestamp={}], not found timestamp, finding the next", symbolDto, timeframe, timestamp);
      return this.getRepository().findAllBySymbolNameAndTimeframeOrderByTimestampAsc(symbolDto.name(), timeframe).filter(candlestick -> candlestick.timestamp().isAfter(timestamp)).next()
          .map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
    })).switchIfEmpty(Mono.defer(() -> {
      log.warn("GetOrNext [symbolDto={}, timeframe={}, timestamp={}], not found timestamp and not find the next", symbolDto, timeframe, timestamp);
      return Mono.empty();
    }));
  }

  public Flux<CandlestickDto> get(SymbolDto symbolDto, Timeframe timeframe) {
    log.debug("Get [symbolDto={}, timeframe={}]", symbolDto, timeframe);
    return this.getRepository().findAllBySymbolNameAndTimeframeOrderByTimestampAsc(symbolDto.name(), timeframe).map(candlestick -> this.getMapper().toDto(symbolDto, candlestick));
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

  public Mono<Void> delete(SymbolDto symbolDto, Timeframe timeframe) {
    log.debug("Delete [symbolDto={}, timeframe={}]", symbolDto, timeframe);
    return this.getRepository().deleteAllBySymbolNameAndTimeframe(symbolDto.name(), timeframe);
  }

  public Mono<Void> deleteAll(SymbolDto symbolDto) {
    log.debug("Delete All [symbolDto={}]", symbolDto);
    return this.getRepository().deleteAllBySymbolName(symbolDto.name());
  }
}
