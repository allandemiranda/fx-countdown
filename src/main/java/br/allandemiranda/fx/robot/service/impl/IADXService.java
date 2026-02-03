package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IADXMapper;
import br.allandemiranda.fx.robot.model.impl.IADX;
import br.allandemiranda.fx.robot.repository.impl.IADXRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IADXService implements InputObjectService<IADX, IADXDto, IADXCreateDto> {

  private final IADXRepository repository;

  private final IADXMapper mapper;

}
