package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.mapper.StochasticMapper;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.repository.StochasticRepository;
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
public class StochasticService {

  private final StochasticRepository repository;

  @Transactional(readOnly = true)
  public Mono<StochasticDto> getStochastic(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findStochastic(chartDto.id(), timestamp).map(stochastic -> StochasticMapper.toStochasticDto(chartDto, stochastic));
  }

  @Transactional(readOnly = true)
  public Flux<StochasticDto> getStochastic(@NonNull ChartDto chartDto) {
    return this.getRepository().findStochastics(chartDto.id()).map(stochastic -> StochasticMapper.toStochasticDto(chartDto, stochastic));
  }

  public Mono<StochasticDto> createStochastic(ChartDto chartDto, StochasticCreateDto stochasticCreateDto) {
    Stochastic model = StochasticMapper.toStochastic(UUID.randomUUID(), chartDto, stochasticCreateDto);
    return this.getRepository().save(model).map(stochastic -> StochasticMapper.toStochasticDto(chartDto, stochastic));
  }

  public Mono<Void> deleteStochastic(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteStochastic(chartDto.id());
  }

}
