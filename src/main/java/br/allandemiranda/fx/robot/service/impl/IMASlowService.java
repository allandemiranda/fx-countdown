package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMASlowMapper;
import br.allandemiranda.fx.robot.model.impl.IMASlow;
import br.allandemiranda.fx.robot.repository.impl.IMASlowRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMASlowService implements InputObjectService<IMASlow, IMASlowDto, IMASlowCreateDto> {

  private final IMASlowRepository repository;

  private final IMASlowMapper mapper;

}
