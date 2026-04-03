package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.repository.ADXRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
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
