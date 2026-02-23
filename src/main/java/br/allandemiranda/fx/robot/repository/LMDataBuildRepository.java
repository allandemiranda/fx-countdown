package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.LMDataBuild;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LMDataBuildRepository extends JpaRepository<LMDataBuild, UUID>, JpaSpecificationExecutor<LMDataBuild> {

}