package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.GarchInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.GarchInput;
import br.allandemiranda.fx.robot.repository.impl.input.GarchInputRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class GarchInputService implements InputDashboardService<GarchInput, GarchInputDto, GarchInputCreateDto> {

  private final GarchInputRepository repository;
  private final GarchInputMapper mapper;

}
