package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.MaSlowMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MaSlow;
import br.allandemiranda.fx.robot.repository.impl.indicator.MaSlowRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MaSlowService implements IndicatorService<MaSlow, MaSlowDto, MaSlowCreateDto> {

  private final MaSlowRepository repository;

  private final MaSlowMapper mapper;

}
