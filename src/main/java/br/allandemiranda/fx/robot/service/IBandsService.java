package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.IBandsDto;
import br.allandemiranda.fx.robot.mapper.IBandsMapper;
import br.allandemiranda.fx.robot.model.IBands;
import br.allandemiranda.fx.robot.repository.IBandsRepository;
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
public class IBandsService {

  private final IBandsRepository repository;

  @Transactional(readOnly = true)
  public Mono<IBandsDto> getIBands(@NonNull ChartDto chartDto) {
    return this.getRepository().findIBands(chartDto.id()).map(iBands -> IBandsMapper.toIBandsDto(chartDto, iBands));
  }

  public Mono<IBandsDto> createIBands(@NonNull ChartDto chartDto, @NonNull IBandsCreateDto iBandsCreateDto) {
    IBands model = IBandsMapper.toIBands(chartDto, iBandsCreateDto);
    return this.getRepository().save(model).map(iBands -> IBandsMapper.toIBandsDto(chartDto, iBands));
  }

  public Mono<Void> deleteIBands(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIBands(chartDto.id());
  }

}
