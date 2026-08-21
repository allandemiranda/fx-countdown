package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.MaFastDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.MaFastMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MaFastEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.MaFastRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class MaFastService implements IndicatorService<MaFastEntry, MaFastDto, MaFastCreateDto> {

  private final MaFastMapper mapper;
  private final MaFastRepository repository;

}
