package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.mapper.TickMapper;
import br.allandemiranda.fx.robot.model.Tick;
import br.allandemiranda.fx.robot.repository.TickRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class TickService {

  private final TickRepository repository;

  @Transactional(readOnly = true)
  public Mono<TickDto> getTick(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findTick(chartDto.id(), timestamp).map(tick -> TickMapper.toTickDto(chartDto, tick));
  }

  @Transactional(readOnly = true)
  public Flux<TickDto> getTick(@NonNull ChartDto chartDto) {
    return this.getRepository().findTicks(chartDto.id()).map(tick -> TickMapper.toTickDto(chartDto, tick));
  }

  public Mono<TickDto> createTick(ChartDto chartDto, TickCreateDto tickCreateDto) {
    Tick model = TickMapper.toTick(UUID.randomUUID(), chartDto, tickCreateDto);
    return this.getRepository().save(model).map(tick -> TickMapper.toTickDto(chartDto, tick));
  }

  public Mono<Void> deleteTick(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteChart(chartDto.id());
  }

}
