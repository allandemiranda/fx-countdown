package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IBandsMapper;
import br.allandemiranda.fx.robot.model.impl.IBands;
import br.allandemiranda.fx.robot.repository.impl.IBandsRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IBandsService implements InputObjectService<IBands, IBandsDto, IBandsCreateDto> {

  private final IBandsRepository repository;

  private final IBandsMapper mapper;

}
