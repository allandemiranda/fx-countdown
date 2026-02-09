package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.MACD;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MACDRepository extends JpaRepository<MACD, UUID>, JpaSpecificationExecutor<MACD> {

}