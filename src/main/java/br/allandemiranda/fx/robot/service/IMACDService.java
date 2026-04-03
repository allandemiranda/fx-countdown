package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.mapper.IMACDMapper;
import br.allandemiranda.fx.robot.model.IMACD;
import br.allandemiranda.fx.robot.repository.IMACDRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
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
