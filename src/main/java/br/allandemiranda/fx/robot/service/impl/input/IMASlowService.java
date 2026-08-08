package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IMASlowMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMASlow;
import br.allandemiranda.fx.robot.repository.impl.input.IMASlowRepository;
import br.allandemiranda.fx.robot.service.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMASlowService implements InputService<IMASlow, IMASlowDto, IMASlowCreateDto> {

  private final IMASlowRepository repository;
  private final IMASlowMapper mapper;

}
