package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MaFastMapper;
import br.allandemiranda.fx.robot.model.impl.MaFast;
import br.allandemiranda.fx.robot.repository.impl.MaFastRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MaFastService implements ChartObjectService<MaFast, MaFastDto, MaFastCreateDto> {

  private final MaFastRepository repository;

  private final MaFastMapper mapper;

}
