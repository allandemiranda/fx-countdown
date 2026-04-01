package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.mapper.MaFastMapper;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.repository.MaFastRepository;
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
public class MaFastService {

  private final MaFastRepository repository;

  @Transactional(readOnly = true)
  public Mono<MaFastDto> getMaFast(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findMaFast(chartDto.id(), timestamp).map(maFast -> MaFastMapper.toMaFastDto(chartDto, maFast));
  }

  @Transactional(readOnly = true)
  public Flux<MaFastDto> getMaFast(@NonNull ChartDto chartDto) {
    return this.getRepository().findMaFasts(chartDto.id()).map(maFast -> MaFastMapper.toMaFastDto(chartDto, maFast));
  }

  public Mono<MaFastDto> createMaFast(ChartDto chartDto, MaFastCreateDto maFastCreateDto) {
    MaFast model = MaFastMapper.toMaFast(UUID.randomUUID(), chartDto, maFastCreateDto);
    return this.getRepository().save(model).map(maFast -> MaFastMapper.toMaFastDto(chartDto, maFast));
  }

  public Mono<Void> deleteMaFast(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteMaFast(chartDto.id());
  }

}
