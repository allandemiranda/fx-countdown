package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.ADXDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.ADXMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.ADXEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.ADXRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class ADXService implements IndicatorService<ADXEntry, ADXDto, ADXCreateDto> {

  private final ADXMapper mapper;
  private final ADXRepository repository;

}
