package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.IMAFastMapper;
import br.allandemiranda.fx.robot.model.impl.IMAFast;
import br.allandemiranda.fx.robot.repository.impl.IMAFastRepository;
import br.allandemiranda.fx.robot.service.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMAFastService implements InputObjectService<IMAFast, IMAFastDto, IMAFastCreateDto> {

  private final IMAFastRepository repository;

  private final IMAFastMapper mapper;

}
