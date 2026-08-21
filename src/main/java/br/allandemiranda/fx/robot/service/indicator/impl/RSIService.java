package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.RSIDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.RSIMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.RSIEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.RSIRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class RSIService implements IndicatorService<RSIEntry, RSIDto, RSICreateDto> {

  private final RSIMapper mapper;
  private final RSIRepository repository;

}
