package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.MACDDto;
import br.allandemiranda.fx.robot.mapper.MACDMapper;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.repository.MACDRepository;
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
public class MACDService {

  private final MACDRepository repository;

  @Transactional(readOnly = true)
  public Mono<MACDDto> getMACD(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findMACD(chartDto.id(), timestamp).map(macd -> MACDMapper.toMACDDto(chartDto, macd));
  }

  @Transactional(readOnly = true)
  public Flux<MACDDto> getMACD(@NonNull ChartDto chartDto) {
    return this.getRepository().findMACDs(chartDto.id()).map(macd -> MACDMapper.toMACDDto(chartDto, macd));
  }

  public Mono<MACDDto> createMACD(ChartDto chartDto, MACDCreateDto macdCreateDto) {
    MACD model = MACDMapper.toMACD(UUID.randomUUID(), chartDto, macdCreateDto);
    return this.getRepository().save(model).map(macd -> MACDMapper.toMACDDto(chartDto, macd));
  }

  public Mono<Void> deleteMACD(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteMACD(chartDto.id());
  }

}
