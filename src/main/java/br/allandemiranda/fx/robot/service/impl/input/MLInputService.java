package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.MLInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.MLInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.MLInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.MLInput;
import br.allandemiranda.fx.robot.repository.impl.input.MLInputRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MLInputService implements InputDashboardService<MLInput, MLInputDto, MLInputCreateDto> {

  private final MLInputRepository repository;

  private final MLInputMapper mapper;

}
