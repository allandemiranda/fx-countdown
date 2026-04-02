package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandlestickRepository extends ChartObjectRepository<Candlestick> {

}