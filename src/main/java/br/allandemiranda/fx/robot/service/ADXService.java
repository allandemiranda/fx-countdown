package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.ADXDto;
import br.allandemiranda.fx.robot.mapper.ADXCreateMapper;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.repository.ADXRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class ADXService {

  private final ADXRepository repository;
  private final ADXMapper mapper;
  private final ADXCreateMapper createMapper;

  public ADXDto create(ADXCreateDto adxCreateDto) {
    ADX entity = this.getCreateMapper().toEntity(adxCreateDto);
    ADX adx = this.getRepository().save(entity);
    return this.getMapper().toDto(adx);
  }

}
