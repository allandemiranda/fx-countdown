package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.mapper.BandsMapper;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.repository.BandsRepository;
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
public class BandsService {

  private final BandsRepository repository;

  @Transactional(readOnly = true)
  public Mono<BandsDto> getBands(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findBands(chartDto.id(), timestamp).map(bands -> BandsMapper.toBandsDto(chartDto, bands));
  }

  @Transactional(readOnly = true)
  public Flux<BandsDto> getBands(@NonNull ChartDto chartDto) {
    return this.getRepository().findBandss(chartDto.id()).map(bands -> BandsMapper.toBandsDto(chartDto, bands));
  }

  public Mono<BandsDto> createBands(ChartDto chartDto, BandsCreateDto bandsCreateDto) {
    Bands model = BandsMapper.toBands(UUID.randomUUID(), chartDto, bandsCreateDto);
    return this.getRepository().save(model).map(bands -> BandsMapper.toBandsDto(chartDto, bands));
  }

  public Mono<Void> deleteBands(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteBands(chartDto.id());
  }

}
