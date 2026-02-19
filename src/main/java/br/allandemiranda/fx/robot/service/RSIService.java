package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.RSICreateDto;
import br.allandemiranda.fx.robot.dto.RSIDto;
import br.allandemiranda.fx.robot.mapper.RSICreateMapper;
import br.allandemiranda.fx.robot.mapper.RSIMapper;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.repository.RSIRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Service
public class RSIService {

  private final RSIRepository repository;
  private final RSIMapper mapper;
  private final RSICreateMapper createMapper;

  public RSIDto create(RSICreateDto rsiCreateDto) {
    RSI entity = this.getCreateMapper().toEntity(rsiCreateDto);
    RSI rsi = this.getRepository().save(entity);
    return this.getMapper().toDto(rsi);
  }

}
