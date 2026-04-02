package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.mapper.MaSlowMapper;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.repository.MaSlowRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class MaSlowService implements ChartObjectService<MaSlow, MaSlowDto, MaSlowCreateDto, MaSlowRepository> {

  private final MaSlowRepository repository;

  private final MaSlowMapper mapper;

}
