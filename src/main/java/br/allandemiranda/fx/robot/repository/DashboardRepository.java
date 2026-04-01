package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Dashboard;
import java.util.UUID;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DashboardRepository extends ReactiveCrudRepository<Dashboard, UUID> {

  @Query("SELECT * FROM dashboard WHERE chart_id = :chartId LIMIT 1")
  Mono<Dashboard> findDashboard(UUID chartId);

  @Query("DELETE FROM dashboard WHERE chart_id = :chartId")
  Mono<Void> deleteDashboard(UUID chartId);

}