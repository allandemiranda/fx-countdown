package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.CandlestickDto;
import br.allandemiranda.fx.robot.mapper.CandlestickCreateMapper;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class CandlestickService {

  private final CandlestickRepository repository;
  private final CandlestickMapper mapper;
  private final CandlestickCreateMapper createMapper;

  public CandlestickDto create(CandlestickCreateDto candlestickCreateDto) {
    Candlestick entity = this.getCreateMapper().toEntity(candlestickCreateDto);
    Candlestick candlestick = this.getRepository().save(entity);
    return this.getMapper().toDto(candlestick);
  }

}
