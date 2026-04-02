package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.GarchInputMapper;
import br.allandemiranda.fx.robot.model.GarchInput;
import br.allandemiranda.fx.robot.repository.GarchInputRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class GarchInputService implements InputObjectService<GarchInput, GarchInputDto, GarchInputCreateDto, GarchInputRepository> {

  private final GarchInputRepository repository;

  private final GarchInputMapper mapper;

}
