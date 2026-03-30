package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Symbol;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SymbolRepository extends ReactiveCrudRepository<Symbol, String> {

}