package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.ADXMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.ADX;
import br.allandemiranda.fx.robot.repository.impl.indicator.ADXRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ADXService implements IndicatorService<ADX, ADXDto, ADXCreateDto> {

  private final ADXRepository repository;

  private final ADXMapper mapper;

}
