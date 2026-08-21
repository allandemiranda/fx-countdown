package br.allandemiranda.fx.robot.service.core;

import br.allandemiranda.fx.robot.dto.core.TickDto;
import br.allandemiranda.fx.robot.dto.core.create.TickCreateDto;
import br.allandemiranda.fx.robot.mapper.core.TickMapper;
import br.allandemiranda.fx.robot.model.core.impl.TickEntity;
import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.repository.core.TickRepository;
import br.allandemiranda.fx.robot.service.utils.ScopeUtils;
import java.time.OffsetDateTime;
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
public class TickService {

  private final TickMapper mapper;
  private final TickRepository repository;

  public Mono<TickDto> create(String symbolName, TickCreateDto tickCreateDto) {
    log.debug("Create an Tick [symbolName={}, tickCreateDto={}]", symbolName, tickCreateDto);
    return this.get(symbolName, tickCreateDto.timestamp()).flatMap(tickDto -> {
      TickEntity model = this.getMapper().toModel(tickDto.id(), symbolName, tickCreateDto);
      log.trace("Create an Tick [symbolName={}, tickCreateDto={}], updating already exist [tickDto={}]", symbolName, tickCreateDto, tickDto);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(tick));
    }).switchIfEmpty(Mono.defer(() -> {
      TickEntity model = this.getMapper().toModel(UUID.randomUUID(), symbolName, tickCreateDto);
      return this.getRepository().save(model).map(tick -> this.getMapper().toDto(tick));
    }));
  }

  public Flux<TickDto> get(String symbolName) {
    log.debug("Get an Tick by symbolName={}", symbolName);
    return this.getRepository().findAllBySymbolNameOrderByTimestampAsc(symbolName).map(tick -> this.getMapper().toDto(tick));
  }

  public Mono<TickDto> get(String symbolName, OffsetDateTime timestamp) {
    log.debug("Get an Tick by symbolName={} and timestamp={}", symbolName, timestamp);
    return this.getRepository().findBySymbolNameAndTimestamp(symbolName, timestamp).map(tick -> this.getMapper().toDto(tick));
  }

  public Flux<TickDto> getBetweenTimestamp(String symbolName, OffsetDateTime start, OffsetDateTime end) {
    log.debug("Get Tickets Equal and After Timestamp [symbolName={}, start={}, end={}]", symbolName, start, end);
    return this.getRepository().findAllBySymbolNameAndTimestampGreaterThanEqualAndTimestampLessThanOrderByTimestampAsc(symbolName, start, end).map(tick -> this.getMapper().toDto(tick));
  }

  public Mono<TickDto> getLastTick(String symbolName) {
    return this.getRepository().findFirstTickEntityBySymbolNameOrderByTimestampDesc(symbolName).map(tick -> this.getMapper().toDto(tick));
  }

  public Mono<ScopeInput> getScope(String symbolName) {
    log.debug("Get Tick scope from [symbolName={}]", symbolName);
    return Mono.zip(this.getRepository().findFirstBySymbolNameOrderByTimestampAsc(symbolName), this.getRepository().findFirstBySymbolNameOrderByTimestampDesc(symbolName)).map(ScopeUtils::getScopeInputByTimeseries);
  }

}
