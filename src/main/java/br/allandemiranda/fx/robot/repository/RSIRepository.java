package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.RSI;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RSIRepository extends JpaRepository<RSI, UUID>, JpaSpecificationExecutor<RSI> {

}