package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Bands;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BandsRepository extends JpaRepository<Bands, UUID>, JpaSpecificationExecutor<Bands> {

}