package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.impl.input.XGBoostInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class XGBoostInputMapper implements InputMapper<XGBoostInput, XGBoostInputDto, XGBoostInputCreateDto> {

  public XGBoostInputDto toDto(ExpertAdvisorDto expertAdvisorDto, XGBoostInput xgBoostInput) {
    return new XGBoostInputDto(xgBoostInput.id(), expertAdvisorDto, xgBoostInput.horizon(), xgBoostInput.maxDepth(), xgBoostInput.eta(), xgBoostInput.subsample(), xgBoostInput.colSampleByTree(), xgBoostInput.minChildWeight(),
        xgBoostInput.lambda(), xgBoostInput.alpha(), xgBoostInput.rounds(), xgBoostInput.minimalLevelAccepted());
  }

  public XGBoostInput toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, XGBoostInputCreateDto xgBoostInputCreateDto) {
    return new XGBoostInput(id, expertAdvisorDto.id(), xgBoostInputCreateDto.horizon(), xgBoostInputCreateDto.maxDepth(), xgBoostInputCreateDto.eta(), xgBoostInputCreateDto.subsample(), xgBoostInputCreateDto.colSampleByTree(),
        xgBoostInputCreateDto.minChildWeight(), xgBoostInputCreateDto.lambda(), xgBoostInputCreateDto.alpha(), xgBoostInputCreateDto.rounds(), xgBoostInputCreateDto.minimalLevelAccepted());
  }
}
