package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.ATRDto;
import br.allandemiranda.fx.robot.mapper.ATRCreateMapper;
import br.allandemiranda.fx.robot.mapper.ATRMapper;
import br.allandemiranda.fx.robot.model.AtrIndicator;
import br.allandemiranda.fx.robot.repository.ATRRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class ATRService {

  private final ATRRepository repository;
  private final ATRMapper mapper;
  private final ATRCreateMapper createMapper;

  public ATRDto createATR(ATRCreateDto atrCreateDto) {
    AtrIndicator entity = this.getCreateMapper().toEntity(atrCreateDto);
    AtrIndicator atr = this.getRepository().save(entity);
    return this.getMapper().toDto(atr);
  }

}
