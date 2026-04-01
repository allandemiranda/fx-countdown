package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
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
public class CandlestickService {

  private final CandlestickRepository repository;

  @Transactional(readOnly = true)
  public Mono<CandlestickDto> getCandlestick(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findCandlestick(chartDto.id(), timestamp).map(candlestick -> CandlestickMapper.toCandlestickDto(chartDto, candlestick));
  }

  @Transactional(readOnly = true)
  public Flux<CandlestickDto> getCandlestick(@NonNull ChartDto chartDto) {
    return this.getRepository().findCandlesticks(chartDto.id()).map(candlestick -> CandlestickMapper.toCandlestickDto(chartDto, candlestick));
  }

  public Mono<CandlestickDto> createCandlestick(ChartDto chartDto, CandlestickCreateDto candlestickCreateDto) {
    Candlestick model = CandlestickMapper.toCandlestick(UUID.randomUUID(), chartDto, candlestickCreateDto);
    return this.getRepository().save(model).map(candlestick -> CandlestickMapper.toCandlestickDto(chartDto, candlestick));
  }

  public Mono<Void> deleteCandlestick(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteCandlestick(chartDto.id());
  }

}
