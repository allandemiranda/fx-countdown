package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MLDataRow;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MLDataRowRepository extends ReactiveCrudRepository<MLDataRow, UUID> {

}