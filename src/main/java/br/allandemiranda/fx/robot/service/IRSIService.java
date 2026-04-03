package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.IRSIMapper;
import br.allandemiranda.fx.robot.model.IRSI;
import br.allandemiranda.fx.robot.repository.IRSIRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
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
