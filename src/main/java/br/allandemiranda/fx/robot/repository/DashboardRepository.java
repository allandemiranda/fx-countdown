package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.contract.InputObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends InputObjectRepository<Dashboard> {

}