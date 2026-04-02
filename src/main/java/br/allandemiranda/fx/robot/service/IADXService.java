package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.mapper.IADXMapper;
import br.allandemiranda.fx.robot.model.IADX;
import br.allandemiranda.fx.robot.repository.IADXRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class IADXService implements InputObjectService<IADX, IADXDto, IADXCreateDto, IADXRepository> {

  private final IADXRepository repository;

  private final IADXMapper mapper;

}
