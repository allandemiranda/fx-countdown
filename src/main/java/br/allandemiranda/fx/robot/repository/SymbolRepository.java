package br.allandemiranda.fx.robot.repository;

import br.allandemiranda.fx.robot.model.Symbol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SymbolRepository extends JpaRepository<Symbol, String>, JpaSpecificationExecutor<Symbol> {

  Optional<Symbol> findFirstByName(String name);
}