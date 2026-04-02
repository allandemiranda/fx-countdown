package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarchForecastRepository extends ChartObjectRepository<GarchForecast> {

}