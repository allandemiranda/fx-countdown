package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.dto.core.TickCreateDto;
import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
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

  public Flux<TickDto> get(SymbolDto symbolDto) {
    log.debug("Get [symbolDto={}]", symbolDto);
    return this.getRepository().findAllBySymbolNameOrderByTimestampAsc(symbolDto.name()).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Flux<TickDto> getAfterTimestamp(SymbolDto symbolDto, OffsetDateTime start) {
    log.debug("Get Equal and After Timestamp [symbolDto={}, start={}]", symbolDto, start);
    return this.getRepository().findAllBySymbolNameAndTimestampGreaterThanEqualOrderByTimestampAsc(symbolDto.name(), start).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Flux<TickDto> getBetweenTimestamp(SymbolDto symbolDto, OffsetDateTime start, OffsetDateTime end) {
    log.debug("Get Equal and After Timestamp [symbolDto={}, start={}, end={}]", symbolDto, start, end);
    return this.getRepository().findAllBySymbolNameAndTimestampGreaterThanEqualAndTimestampLessThanOrderByTimestampAsc(symbolDto.name(), start, end).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Mono<TickDto> create(SymbolDto symbolDto, TickCreateDto tickCreateDto) {
    log.debug("Get [symbolDto={}, tickCreateDto={}]", symbolDto, tickCreateDto);
    return this.get(symbolDto, tickCreateDto.timestamp()).flatMap(tickDto -> {
      log.trace("Create [symbolDto={}, tickCreateDto={}], object already exist [tickDto={}]", symbolDto, tickCreateDto, tickDto);
      Tick model = this.getMapper().toModel(tickDto.id(), symbolDto, tickCreateDto);
      log.trace("Create [symbolDto={}, tickCreateDto={}], updating already exist [tick={}]", symbolDto, tickCreateDto, model);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(symbolDto, tick));
    }).switchIfEmpty(Mono.defer(() -> {
      Tick model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, tickCreateDto);
      log.trace("Create [symbolDto={}, tickCreateDto={}], new object generated to save [tick={}]", symbolDto, tickCreateDto, model);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(symbolDto, tick));
    }));
  }

  public Mono<ScopeInputCreateDto> getScope(SymbolDto symbolDto) {
    log.debug("Get scope [symbolDto={}]", symbolDto);
    return Mono.zip(this.getRepository().findFirstBySymbolNameOrderByTimestampAsc(symbolDto.name()), this.getRepository().findFirstBySymbolNameOrderByTimestampDesc(symbolDto.name()))
        .flatMap(objects -> Mono.just(new ScopeInputCreateDto(objects.getT1().timestamp(), objects.getT2().timestamp())));
  }

}
