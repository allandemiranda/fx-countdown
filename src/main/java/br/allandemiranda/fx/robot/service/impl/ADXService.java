package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.ADXMapper;
import br.allandemiranda.fx.robot.model.impl.ADX;
import br.allandemiranda.fx.robot.repository.impl.ADXRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ADXService implements ChartObjectService<ADX, ADXDto, ADXCreateDto> {

  private final ADXRepository repository;

  private final ADXMapper mapper;

}
