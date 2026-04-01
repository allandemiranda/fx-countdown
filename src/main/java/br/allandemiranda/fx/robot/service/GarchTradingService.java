package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.dto.GarchTradingDto;
import br.allandemiranda.fx.robot.mapper.GarchTradingMapper;
import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.repository.GarchTradingRepository;
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
public class GarchTradingService {

  private final GarchTradingRepository repository;

  @Transactional(readOnly = true)
  public Mono<GarchTradingDto> getGarchTrading(@NonNull ChartDto chartDto, OffsetDateTime timestamp) {
    return this.getRepository().findGarchTrading(chartDto.id(), timestamp).map(garchTrading -> GarchTradingMapper.toGarchTradingDto(chartDto, garchTrading));
  }

  @Transactional(readOnly = true)
  public Flux<GarchTradingDto> getGarchTrading(@NonNull ChartDto chartDto) {
    return this.getRepository().findGarchTradings(chartDto.id()).map(garchTrading -> GarchTradingMapper.toGarchTradingDto(chartDto, garchTrading));
  }

  public Mono<GarchTradingDto> createGarchTrading(ChartDto chartDto, GarchTradingCreateDto garchTradingCreateDto) {
    GarchTrading model = GarchTradingMapper.toGarchTrading(UUID.randomUUID(), chartDto, garchTradingCreateDto);
    return this.getRepository().save(model).map(garchTrading -> GarchTradingMapper.toGarchTradingDto(chartDto, garchTrading));
  }

  public Mono<Void> deleteGarchTrading(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteGarchTrading(chartDto.id());
  }

}
