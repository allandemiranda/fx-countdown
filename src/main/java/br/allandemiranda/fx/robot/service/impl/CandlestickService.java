package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.CandlestickMapper;
import br.allandemiranda.fx.robot.model.impl.Candlestick;
import br.allandemiranda.fx.robot.repository.impl.CandlestickRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class CandlestickService implements ChartObjectService<Candlestick, CandlestickDto, CandlestickCreateDto> {

  private final CandlestickRepository repository;

  private final CandlestickMapper mapper;

}
