package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.StochasticCreateDto;
import br.allandemiranda.fx.robot.dto.StochasticDto;
import br.allandemiranda.fx.robot.mapper.StochasticCreateMapper;
import br.allandemiranda.fx.robot.mapper.StochasticMapper;
import br.allandemiranda.fx.robot.model.Stochastic;
import br.allandemiranda.fx.robot.repository.StochasticRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class StochasticService {

  private final StochasticRepository repository;
  private final StochasticMapper mapper;
  private final StochasticCreateMapper createMapper;

  public StochasticDto create(StochasticCreateDto stochasticCreateDto) {
    Stochastic entity = this.getCreateMapper().toEntity(stochasticCreateDto);
    Stochastic stochastic = this.getRepository().save(entity);
    return this.getMapper().toDto(stochastic);
  }

}
