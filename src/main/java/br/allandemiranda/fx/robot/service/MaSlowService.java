package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.MaSlowCreateMapper;
import br.allandemiranda.fx.robot.mapper.MaSlowMapper;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.repository.MaSlowRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Service
public class MaSlowService {

  private final MaSlowRepository repository;
  private final MaSlowMapper mapper;
  private final MaSlowCreateMapper createMapper;

  public MaSlowDto create(MaSlowCreateDto maSlowCreateDto) {
    MaSlow entity = this.getCreateMapper().toEntity(maSlowCreateDto);
    MaSlow maSlow = this.getRepository().save(entity);
    return this.getMapper().toDto(maSlow);
  }

}
