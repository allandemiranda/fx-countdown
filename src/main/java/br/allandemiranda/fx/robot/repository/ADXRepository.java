package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ADX;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ADXRepository extends JpaRepository<ADX, UUID>, JpaSpecificationExecutor<ADX> {

}