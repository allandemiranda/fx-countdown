package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IMAFastMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMAFast;
import br.allandemiranda.fx.robot.repository.impl.input.IMAFastRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMAFastService implements InputDashboardService<IMAFast, IMAFastDto, IMAFastCreateDto> {

  private final IMAFastRepository repository;

  private final IMAFastMapper mapper;

}
