package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.base.TickDto;
import br.allandemiranda.fx.robot.dto.create.TickCreateDto;
import br.allandemiranda.fx.robot.mapper.TickMapper;
import br.allandemiranda.fx.robot.model.Tick;
import br.allandemiranda.fx.robot.repository.TickRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
@Service
public class TickService {

  private final TickRepository repository;
  private final TickMapper mapper;

  public Mono<TickDto> getTick(SymbolDto symbolDto, OffsetDateTime timestamp) {
    return this.getRepository().findBySymbolNameAndTimestamp(symbolDto.name(), timestamp).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Flux<TickDto> getTick(SymbolDto symbolDto) {
    return this.getRepository().findAllBySymbolName(symbolDto.name()).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Mono<TickDto> createTick(SymbolDto symbolDto, TickCreateDto tickCreateDto) {
    Tick model = this.getMapper().toModel(UUID.randomUUID(), symbolDto, tickCreateDto);
    return this.getRepository().save(model).map(tick -> this.getMapper().toDto(symbolDto, tick));
  }

  public Mono<Void> deleteTick(SymbolDto symbolDto) {
    return this.getRepository().deleteAllBySymbolName(symbolDto.name());
  }

}
