package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.MaFastDto;
import br.allandemiranda.fx.robot.mapper.MaFastCreateMapper;
import br.allandemiranda.fx.robot.mapper.MaFastMapper;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.repository.MaFastRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class MaFastService {

  private final MaFastRepository repository;
  private final MaFastMapper mapper;
  private final MaFastCreateMapper createMapper;

  public MaFastDto create(MaFastCreateDto maFastCreateDto) {
    MaFast entity = this.getCreateMapper().toEntity(maFastCreateDto);
    MaFast maFast = this.getRepository().save(entity);
    return this.getMapper().toDto(maFast);
  }

}
