package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.ScriptInfo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ScriptInfoRepository extends JpaRepository<ScriptInfo, UUID>, JpaSpecificationExecutor<ScriptInfo> {

}