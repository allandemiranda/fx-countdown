package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.MaSlowMapper;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.repository.MaSlowRepository;
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
public class MaSlowService {

  private final MaSlowRepository repository;

  @Transactional(readOnly = true)
  public Mono<MaSlowDto> getMaSlow(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findMaSlow(chartDto.id(), timestamp).map(maSlow -> MaSlowMapper.toMaSlowDto(chartDto, maSlow));
  }

  @Transactional(readOnly = true)
  public Flux<MaSlowDto> getMaSlow(@NonNull ChartDto chartDto) {
    return this.getRepository().findMaSlows(chartDto.id()).map(maSlow -> MaSlowMapper.toMaSlowDto(chartDto, maSlow));
  }

  public Mono<MaSlowDto> createMaSlow(ChartDto chartDto, MaSlowCreateDto maSlowCreateDto) {
    MaSlow model = MaSlowMapper.toMaSlow(UUID.randomUUID(), chartDto, maSlowCreateDto);
    return this.getRepository().save(model).map(maSlow -> MaSlowMapper.toMaSlowDto(chartDto, maSlow));
  }

  public Mono<Void> deleteMaSlow(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteMaSlow(chartDto.id());
  }

}
