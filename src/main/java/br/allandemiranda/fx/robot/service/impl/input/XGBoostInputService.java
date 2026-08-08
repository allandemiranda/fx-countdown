package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.XGBoostInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.XGBoostInput;
import br.allandemiranda.fx.robot.repository.impl.input.XGBoostInputRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class XGBoostInputService implements InputDashboardService<XGBoostInput, XGBoostInputDto, XGBoostInputCreateDto> {

  private final XGBoostInputRepository repository;
  private final XGBoostInputMapper mapper;

}
