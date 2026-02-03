package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.impl.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IRSIMapper;
import br.allandemiranda.fx.robot.model.impl.IRSI;
import br.allandemiranda.fx.robot.repository.impl.IRSIRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IRSIService implements InputObjectService<IRSI, IRSIDto, IRSICreateDto> {

  private final IRSIRepository repository;

  private final IRSIMapper mapper;

}
