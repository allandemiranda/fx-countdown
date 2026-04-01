package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.GarchInputMapper;
import br.allandemiranda.fx.robot.model.GarchInput;
import br.allandemiranda.fx.robot.repository.GarchInputRepository;
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
public class GarchInputService {

  private final GarchInputRepository repository;

  @Transactional(readOnly = true)
  public Mono<GarchInputDto> getGarchInput(@NonNull ChartDto chartDto) {
    return this.getRepository().findGarchInput(chartDto.id()).map(garchInput -> GarchInputMapper.toGarchInputDto(chartDto, garchInput));
  }

  public Mono<GarchInputDto> createGarchInput(@NonNull ChartDto chartDto, @NonNull GarchInputCreateDto garchInputCreateDto) {
    GarchInput model = GarchInputMapper.toGarchInput(chartDto, garchInputCreateDto);
    return this.getRepository().save(model).map(garchInput -> GarchInputMapper.toGarchInputDto(chartDto, garchInput));
  }

  public Mono<Void> deleteGarchInput(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteGarchInput(chartDto.id());
  }

}
