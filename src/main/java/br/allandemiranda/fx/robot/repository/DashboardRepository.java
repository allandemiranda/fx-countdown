package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.Dashboard;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DashboardRepository extends JpaRepository<Dashboard, UUID>, JpaSpecificationExecutor<Dashboard> {

  Optional<Dashboard> findFirstByChart_Symbol_NameAndChart_Period(String name, Timeframe period);
}