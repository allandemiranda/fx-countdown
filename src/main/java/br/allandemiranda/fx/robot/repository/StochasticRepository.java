package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Stochastic;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StochasticRepository extends JpaRepository<Stochastic, UUID>, JpaSpecificationExecutor<Stochastic> {

}