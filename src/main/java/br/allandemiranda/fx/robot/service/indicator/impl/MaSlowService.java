package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.MaSlowMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MaSlowEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.MaSlowRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class MaSlowService implements IndicatorService<MaSlowEntry, MaSlowDto, MaSlowCreateDto> {

  private final MaSlowMapper mapper;
  private final MaSlowRepository repository;

}
