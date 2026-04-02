package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.base.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.IMAFastMapper;
import br.allandemiranda.fx.robot.model.IMAFast;
import br.allandemiranda.fx.robot.repository.IMAFastRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class IMAFastService implements InputObjectService<IMAFast, IMAFastDto, IMAFastCreateDto, IMAFastRepository> {

  private final IMAFastRepository repository;

  private final IMAFastMapper mapper;

}
