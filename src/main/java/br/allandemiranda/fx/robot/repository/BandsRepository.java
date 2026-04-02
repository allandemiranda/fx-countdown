package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandsRepository extends ChartObjectRepository<Bands> {

}