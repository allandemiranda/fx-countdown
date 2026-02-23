package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.repository.LMDataBuildRepository;
import java.util.concurrent.Executor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class LMDataBuildService {

  private final LMDataBuildRepository repository;
  private final Executor executor;



}
