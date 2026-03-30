package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchForecast;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GarchForecastRepository extends ReactiveCrudRepository<GarchForecast, UUID> {

}