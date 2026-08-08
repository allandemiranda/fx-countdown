package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.ScopeInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.ScopeInput;
import br.allandemiranda.fx.robot.repository.impl.input.ScopeInputRepository;
import br.allandemiranda.fx.robot.service.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ScopeInputService implements InputService<ScopeInput, ScopeInputDto, ScopeInputCreateDto> {

  private final ScopeInputRepository repository;
  private final ScopeInputMapper mapper;
}
