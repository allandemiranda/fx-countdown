package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.repository.ADXRepository;
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
public class ADXService {

  private final ADXRepository repository;

  @Transactional(readOnly = true)
  public Mono<ADXDto> getADX(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findADX(chartDto.id(), timestamp).map(adx -> ADXMapper.toADXDto(chartDto, adx));
  }

  @Transactional(readOnly = true)
  public Flux<ADXDto> getADX(@NonNull ChartDto chartDto) {
    return this.getRepository().findADXs(chartDto.id()).map(adx -> ADXMapper.toADXDto(chartDto, adx));
  }

  public Mono<ADXDto> createADX(ChartDto chartDto, ADXCreateDto adxCreateDto) {
    ADX model = ADXMapper.toADX(UUID.randomUUID(), chartDto, adxCreateDto);
    return this.getRepository().save(model).map(adx -> ADXMapper.toADXDto(chartDto, adx));
  }

  public Mono<Void> deleteADX(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteADX(chartDto.id());
  }

}
