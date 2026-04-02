package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.base.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.IMASlowMapper;
import br.allandemiranda.fx.robot.model.IMASlow;
import br.allandemiranda.fx.robot.repository.IMASlowRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class IMASlowService implements InputObjectService<IMASlow, IMASlowDto, IMASlowCreateDto, IMASlowRepository> {

  private final IMASlowRepository repository;

  private final IMASlowMapper mapper;

}
