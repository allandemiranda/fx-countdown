package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.RSICreateDto;
import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.mapper.RSIMapper;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.repository.RSIRepository;
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
public class RSIService {

  private final RSIRepository repository;

  @Transactional(readOnly = true)
  public Mono<RSIDto> getRSI(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findRSI(chartDto.id(), timestamp).map(rsi -> RSIMapper.toRSIDto(chartDto, rsi));
  }

  @Transactional(readOnly = true)
  public Flux<RSIDto> getRSI(@NonNull ChartDto chartDto) {
    return this.getRepository().findRSIs(chartDto.id()).map(rsi -> RSIMapper.toRSIDto(chartDto, rsi));
  }

  public Mono<RSIDto> createRSI(ChartDto chartDto, RSICreateDto rsiCreateDto) {
    RSI model = RSIMapper.toRSI(UUID.randomUUID(), chartDto, rsiCreateDto);
    return this.getRepository().save(model).map(rsi -> RSIMapper.toRSIDto(chartDto, rsi));
  }

  public Mono<Void> deleteRSI(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteRSI(chartDto.id());
  }

}
