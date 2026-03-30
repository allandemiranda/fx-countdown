package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.IMACD;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IMACDRepository extends ReactiveCrudRepository<IMACD, UUID> {

}