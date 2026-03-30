package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchTrading;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GarchTradingRepository extends ReactiveCrudRepository<GarchTrading, UUID> {

}