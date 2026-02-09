package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ATR;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ATRRepository extends JpaRepository<ATR, UUID>, JpaSpecificationExecutor<ATR> {

}