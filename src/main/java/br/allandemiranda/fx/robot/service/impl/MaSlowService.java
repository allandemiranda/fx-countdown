package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MaSlowMapper;
import br.allandemiranda.fx.robot.model.impl.MaSlow;
import br.allandemiranda.fx.robot.repository.impl.MaSlowRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MaSlowService implements ChartObjectService<MaSlow, MaSlowDto, MaSlowCreateDto> {

  private final MaSlowRepository repository;

  private final MaSlowMapper mapper;

}
