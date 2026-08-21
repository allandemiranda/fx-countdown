package br.allandemiranda.fx.robot.repository.core;

import br.allandemiranda.fx.robot.model.core.impl.SymbolEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends ReactiveCrudRepository<SymbolEntity, String> {

}
