package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.IBandsMapper;
import br.allandemiranda.fx.robot.model.IBands;
import br.allandemiranda.fx.robot.repository.IBandsRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMACDService implements InputObjectService<IBands, IBandsDto, IBandsCreateDto> {

  private final IBandsRepository repository;

  private final IBandsMapper mapper;

}
