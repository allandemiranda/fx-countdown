package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IATRDto;
import br.allandemiranda.fx.robot.dto.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.IATRMapper;
import br.allandemiranda.fx.robot.model.IATR;
import br.allandemiranda.fx.robot.repository.IATRRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
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
