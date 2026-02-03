package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.impl.RSIMapper;
import br.allandemiranda.fx.robot.model.impl.RSI;
import br.allandemiranda.fx.robot.repository.impl.RSIRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class RSIService implements ChartObjectService<RSI, RSIDto, RSICreateDto> {

  private final RSIRepository repository;

  private final RSIMapper mapper;

}
