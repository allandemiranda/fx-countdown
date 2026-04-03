package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.MaFastMapper;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.repository.MaFastRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
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
