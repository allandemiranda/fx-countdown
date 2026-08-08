package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IBandsMapper;
import br.allandemiranda.fx.robot.model.impl.input.IBands;
import br.allandemiranda.fx.robot.repository.impl.input.IBandsRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IBandsService implements InputDashboardService<IBands, IBandsDto, IBandsCreateDto> {

  private final IBandsRepository repository;
  private final IBandsMapper mapper;

}
