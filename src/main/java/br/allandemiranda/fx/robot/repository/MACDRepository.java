package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MACDRepository extends ChartObjectRepository<MACD> {

}