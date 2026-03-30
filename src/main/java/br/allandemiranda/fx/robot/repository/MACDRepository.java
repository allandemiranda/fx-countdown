package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MACD;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MACDRepository extends ReactiveCrudRepository<MACD, UUID> {

}