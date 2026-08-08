package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.MACDMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MACD;
import br.allandemiranda.fx.robot.repository.impl.indicator.MACDRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MACDService implements IndicatorService<MACD, MACDDto, MACDCreateDto> {

  private final MACDRepository repository;

  private final MACDMapper mapper;

}
