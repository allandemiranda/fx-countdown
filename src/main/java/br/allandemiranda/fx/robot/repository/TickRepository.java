package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Tick;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TickRepository extends JpaRepository<Tick, UUID>, JpaSpecificationExecutor<Tick> {

}