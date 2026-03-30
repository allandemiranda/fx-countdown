package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Bands;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BandsRepository extends ReactiveCrudRepository<Bands, UUID> {

}