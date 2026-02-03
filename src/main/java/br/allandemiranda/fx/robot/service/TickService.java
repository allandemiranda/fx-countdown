package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.impl.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.impl.base.TickDto;
import br.allandemiranda.fx.robot.dto.impl.create.TickCreateDto;
import br.allandemiranda.fx.robot.mapper.TickMapper;
import br.allandemiranda.fx.robot.model.Tick;
import br.allandemiranda.fx.robot.repository.TickRepository;
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
public class TickService {

  private final TickRepository repository;
  private final TickMapper mapper;

  public Mono<TickDto> get(SymbolDto symbolDto, OffsetDateTime timestamp) {
    log.debug("Get [symbolDto={}, timestamp={}]", symbolDto, timestamp);
    return this.getRepository().findBySymbolNameAndTimestamp(symbolDto.name(), timestamp).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Mono<TickDto> getOrNext(SymbolDto symbolDto, OffsetDateTime timestamp) {
    log.debug("GetOrNext [symbolDto={}, timestamp={}]", symbolDto, timestamp);
    return this.getRepository().findBySymbolNameAndTimestamp(symbolDto.name(), timestamp).map(tick -> this.getMapper().toDto(symbolDto, tick))
        .switchIfEmpty(Mono.defer(() -> {
          log.trace("GetOrNext [symbolDto={}, timestamp={}], not found timestamp, finding the next tick", symbolDto, timestamp);
          return this.get(symbolDto).filter(tickDto -> tickDto.timestamp().isAfter(timestamp)).next();
        }))
        .switchIfEmpty(Mono.defer(() -> {
          log.warn("GetOrNext [symbolDto={}, timestamp={}], not found timestamp and not find the next tick", symbolDto, timestamp);
          return Mono.empty();
        }));
  }

  public Flux<TickDto> get(SymbolDto symbolDto) {
    log.debug("Get [symbolDto={}]", symbolDto);
    return this.getRepository().findAllBySymbolNameOrderByTimestampAsc(symbolDto.name()).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Mono<TickDto> create(SymbolDto symbolDto, TickCreateDto tickCreateDto) {
    log.debug("Get [symbolDto={}, tickCreateDto={}]", symbolDto, tickCreateDto);
    return this.get(symbolDto, tickCreateDto.timestamp()).flatMap(tickDto -> {
      log.trace("Create [symbolDto={}, tickCreateDto={}], object already exist [tickDto={}]", symbolDto, tickCreateDto, tickDto);
      return this.getRepository().save(this.getMapper().toModel(tickDto.id(), symbolDto, tickCreateDto)).map(tick -> this.getMapper().toDto(symbolDto, tick));
    }).switchIfEmpty(Mono.defer(() -> {
      Tick model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, tickCreateDto);
      log.trace("Create [symbolDto={}, tickCreateDto={}], new object generated to save [tick={}]", symbolDto, tickCreateDto, model);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(symbolDto, tick));
    }));
  }

  public Mono<Void> delete(TickDto tickDto) {
    log.debug("Delete [tickDto={}]", tickDto);
    return this.getRepository().deleteBySymbolNameAndTimestamp(tickDto.symbolDto().name(), tickDto.timestamp());
  }

  public Mono<Void> deleteAll(SymbolDto symbolDto) {
    log.debug("Delete All [symbolDto={}]", symbolDto);
    return this.getRepository().deleteAllBySymbolName(symbolDto.name());
  }

}
