package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.BandsDto;
import br.allandemiranda.fx.robot.mapper.BandsCreateMapper;
import br.allandemiranda.fx.robot.mapper.BandsMapper;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.repository.BandsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class BandsService {

  private final BandsRepository repository;
  private final BandsMapper mapper;
  private final BandsCreateMapper createMapper;

  public BandsDto create(BandsCreateDto bandsCreateDto) {
    Bands entity = this.getCreateMapper().toEntity(bandsCreateDto);
    Bands bands = this.getRepository().save(entity);
    return this.getMapper().toDto(bands);
  }

}
