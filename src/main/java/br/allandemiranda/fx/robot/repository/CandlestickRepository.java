package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Candlestick;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CandlestickRepository extends ReactiveCrudRepository<Candlestick, UUID> {

}