package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.GarchInputMapper;
import br.allandemiranda.fx.robot.model.impl.GarchInput;
import br.allandemiranda.fx.robot.repository.impl.GarchInputRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class GarchInputService implements InputObjectService<GarchInput, GarchInputDto, GarchInputCreateDto> {

  private final GarchInputRepository repository;

  private final GarchInputMapper mapper;

}
