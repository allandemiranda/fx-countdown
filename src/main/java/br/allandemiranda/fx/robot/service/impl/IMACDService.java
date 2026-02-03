package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMACDMapper;
import br.allandemiranda.fx.robot.model.impl.IMACD;
import br.allandemiranda.fx.robot.repository.impl.IMACDRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMACDService implements InputObjectService<IMACD, IMACDDto, IMACDCreateDto> {

  private final IMACDRepository repository;

  private final IMACDMapper mapper;

}
