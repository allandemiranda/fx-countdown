package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMAFast;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IMAFastRepository extends ReactiveCrudRepository<IMAFast, UUID> {

}