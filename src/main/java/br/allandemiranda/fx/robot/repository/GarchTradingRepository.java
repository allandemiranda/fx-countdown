package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarchTradingRepository extends ChartObjectRepository<GarchTrading> {

}