package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChartRepository extends ReactiveCrudRepository<Chart, UUID> {

}