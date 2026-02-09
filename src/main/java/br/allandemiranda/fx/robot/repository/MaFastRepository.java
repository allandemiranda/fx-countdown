package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaFast;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaFastRepository extends JpaRepository<MaFast, UUID>, JpaSpecificationExecutor<MaFast> {

}