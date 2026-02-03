package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IATRMapper;
import br.allandemiranda.fx.robot.model.impl.IATR;
import br.allandemiranda.fx.robot.repository.impl.IATRRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IATRService implements InputObjectService<IATR, IATRDto, IATRCreateDto> {

  private final IATRRepository repository;

  private final IATRMapper mapper;

}
