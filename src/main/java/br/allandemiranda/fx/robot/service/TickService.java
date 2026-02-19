package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.TickCreateDto;
import br.allandemiranda.fx.robot.dto.TickDto;
import br.allandemiranda.fx.robot.mapper.TickCreateMapper;
import br.allandemiranda.fx.robot.mapper.TickMapper;
import br.allandemiranda.fx.robot.model.Tick;
import br.allandemiranda.fx.robot.repository.TickRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class TickService {

  private final TickRepository repository;
  private final TickMapper mapper;
  private final TickCreateMapper createMapper;

  public TickDto create(TickCreateDto tickCreateDto) {
    Tick entity = this.getCreateMapper().toEntity(tickCreateDto);
    Tick tick = this.getRepository().save(entity);
    return this.getMapper().toDto(tick);
  }

}
