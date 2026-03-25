package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.AtrIndicator;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ATRRepository extends JpaRepository<AtrIndicator, UUID>, JpaSpecificationExecutor<AtrIndicator> {

}