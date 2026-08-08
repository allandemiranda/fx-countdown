package br.allandemiranda.fx.robot.repository.impl;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Candlestick;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CandlestickRepository extends ReactiveCrudRepository<Candlestick, UUID> {

  Mono<Candlestick> findBySymbolNameAndTimeframeAndTimestamp(String symbolName, Timeframe timeframe, OffsetDateTime timestamp);

  Flux<Candlestick> findAllBySymbolNameAndTimeframeOrderByTimestampAsc(String symbolName, Timeframe timeframe);

  Mono<Void> deleteAllBySymbolNameAndTimeframe(String symbolName, Timeframe timeframe);

  Mono<Void> deleteAllBySymbolName(String symbolName);
}