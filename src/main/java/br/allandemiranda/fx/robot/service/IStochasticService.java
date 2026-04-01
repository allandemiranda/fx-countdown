package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.IStochasticDto;
import br.allandemiranda.fx.robot.mapper.IStochasticMapper;
import br.allandemiranda.fx.robot.model.IStochastic;
import br.allandemiranda.fx.robot.repository.IStochasticRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class IStochasticService {

  private final IStochasticRepository repository;

  @Transactional(readOnly = true)
  public Mono<IStochasticDto> getIStochastic(@NonNull ChartDto chartDto) {
    return this.getRepository().findIStochastic(chartDto.id()).map(iStochastic -> IStochasticMapper.toIStochasticDto(chartDto, iStochastic));
  }

  public Mono<IStochasticDto> createIStochastic(@NonNull ChartDto chartDto, @NonNull IStochasticCreateDto iStochasticCreateDto) {
    IStochastic model = IStochasticMapper.toIStochastic(chartDto, iStochasticCreateDto);
    return this.getRepository().save(model).map(iStochastic -> IStochasticMapper.toIStochasticDto(chartDto, iStochastic));
  }

  public Mono<Void> deleteIStochastic(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIStochastic(chartDto.id());
  }

}
