package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.ATRMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.ATR;
import br.allandemiranda.fx.robot.repository.impl.indicator.ATRRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ATRService implements IndicatorService<ATR, ATRDto, ATRCreateDto> {

  private final ATRRepository repository;

  private final ATRMapper mapper;

}
