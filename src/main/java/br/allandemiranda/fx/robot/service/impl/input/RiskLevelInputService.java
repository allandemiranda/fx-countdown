package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.RiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.RiskLevelInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.RiskLevelInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.RiskLevelInput;
import br.allandemiranda.fx.robot.repository.impl.input.RiskLevelInputRepository;
import br.allandemiranda.fx.robot.service.InputDashboardService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class RiskLevelInputService implements InputDashboardService<RiskLevelInput, RiskLevelInputDto, RiskLevelInputCreateDto> {

  private final RiskLevelInputRepository repository;
  private final RiskLevelInputMapper mapper;
}
