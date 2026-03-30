package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaSlow;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MaSlowRepository extends ReactiveCrudRepository<MaSlow, UUID> {

}