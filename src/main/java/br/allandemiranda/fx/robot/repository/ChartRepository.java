package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Chart;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChartRepository extends JpaRepository<Chart, UUID>, JpaSpecificationExecutor<Chart> {

}