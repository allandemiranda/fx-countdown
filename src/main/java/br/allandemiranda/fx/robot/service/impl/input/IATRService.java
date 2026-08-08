package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IATRMapper;
import br.allandemiranda.fx.robot.model.impl.input.IATR;
import br.allandemiranda.fx.robot.repository.impl.input.IATRRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IATRService implements InputDashboardService<IATR, IATRDto, IATRCreateDto> {

  private final IATRRepository repository;
  private final IATRMapper mapper;

}
