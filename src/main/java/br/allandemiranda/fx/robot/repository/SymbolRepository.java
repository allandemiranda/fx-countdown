package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Symbol;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends ReactiveCrudRepository<Symbol, String> {

}