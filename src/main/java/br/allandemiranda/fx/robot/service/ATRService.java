package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.dto.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.mapper.ATRCreateMapper;
import br.allandemiranda.fx.robot.mapper.ATRMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.model.AtrIndicator;
import br.allandemiranda.fx.robot.repository.ATRRepository;
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
public class ATRService {

  private final ATRRepository repository;

  @Transactional(readOnly = true)
  public Mono<ADXDto> getATR(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findATR(chartDto.id(), timestamp).map(atr -> ATRMapper.toATRDto(chartDto, atr));
  }

  @Transactional(readOnly = true)
  public Flux<ATRDto> getATR(@NonNull ChartDto chartDto) {
    return this.getRepository().findATRs(chartDto.id()).map(atr -> ATRMapper.toATRDto(chartDto, atr));
  }

  public Mono<ATRDto> createATR(ChartDto chartDto, ATRCreateDto atrCreateDto) {
    ATR model = ATRMapper.toATR(UUID.randomUUID(), chartDto, atrCreateDto);
    return this.getRepository().save(model).map(atr -> ATRMapper.toATRDto(chartDto, atr));
  }

  public Mono<Void> deleteATR(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteATR(chartDto.id());
  }

}
