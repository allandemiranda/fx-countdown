package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputDto;
import br.allandemiranda.fx.robot.mapper.impl.input.PriceRiskLevelInputMapper;
import br.allandemiranda.fx.robot.model.impl.input.PriceRiskLevelInput;
import br.allandemiranda.fx.robot.repository.impl.input.PriceRiskLevelInputRepository;
import br.allandemiranda.fx.robot.service.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class PriceRiskLevelInputService implements InputService<PriceRiskLevelInput, PriceRiskLevelInputDto, PriceRiskLevelInputCreateDto> {

  private final PriceRiskLevelInputRepository repository;
  private final PriceRiskLevelInputMapper mapper;
}
