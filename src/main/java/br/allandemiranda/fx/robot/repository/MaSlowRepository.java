package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MaSlow;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaSlowRepository extends JpaRepository<MaSlow, UUID>, JpaSpecificationExecutor<MaSlow> {

}