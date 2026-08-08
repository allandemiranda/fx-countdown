package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IADXMapper;
import br.allandemiranda.fx.robot.model.impl.input.IADX;
import br.allandemiranda.fx.robot.repository.impl.input.IADXRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IADXService implements InputDashboardService<IADX, IADXDto, IADXCreateDto> {

  private final IADXRepository repository;

  private final IADXMapper mapper;

}
