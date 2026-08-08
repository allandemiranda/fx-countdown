package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.RSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.RSIMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.RSI;
import br.allandemiranda.fx.robot.repository.impl.indicator.RSIRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class RSIService implements IndicatorService<RSI, RSIDto, RSICreateDto> {

  private final RSIRepository repository;

  private final RSIMapper mapper;

}
