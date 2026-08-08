package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.MaFastMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.MaFast;
import br.allandemiranda.fx.robot.repository.impl.indicator.MaFastRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MaFastService implements IndicatorService<MaFast, MaFastDto, MaFastCreateDto> {

  private final MaFastRepository repository;

  private final MaFastMapper mapper;

}
