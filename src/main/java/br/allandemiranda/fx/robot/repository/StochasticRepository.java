package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StochasticRepository extends ChartObjectRepository<Stochastic> {

}