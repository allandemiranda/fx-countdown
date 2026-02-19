package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.MACDDto;
import br.allandemiranda.fx.robot.mapper.MACDCreateMapper;
import br.allandemiranda.fx.robot.mapper.MACDMapper;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.repository.MACDRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class MACDService {

  private final MACDRepository repository;
  private final MACDMapper mapper;
  private final MACDCreateMapper createMapper;

  public MACDDto create(MACDCreateDto macdCreateDto) {
    MACD entity = this.getCreateMapper().toEntity(macdCreateDto);
    MACD macd = this.getRepository().save(entity);
    return this.getMapper().toDto(macd);
  }

}
