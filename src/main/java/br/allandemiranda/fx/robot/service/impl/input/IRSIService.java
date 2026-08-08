package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSIDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IRSIMapper;
import br.allandemiranda.fx.robot.model.impl.input.IRSI;
import br.allandemiranda.fx.robot.repository.impl.input.IRSIRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IRSIService implements InputDashboardService<IRSI, IRSIDto, IRSICreateDto> {

  private final IRSIRepository repository;
  private final IRSIMapper mapper;

}
