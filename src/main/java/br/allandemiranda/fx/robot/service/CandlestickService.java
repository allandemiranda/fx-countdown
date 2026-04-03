package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.mapper.CandlestickMapper;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.repository.CandlestickRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
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
