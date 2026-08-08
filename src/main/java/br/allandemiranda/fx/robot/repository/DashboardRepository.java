package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Dashboard;
import java.util.UUID;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends ReactiveCrudRepository<Dashboard, UUID> {

}