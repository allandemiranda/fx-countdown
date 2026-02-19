package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.repository.TickRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Service
public class TickService {

  private final TickRepository tickRepository;

}
