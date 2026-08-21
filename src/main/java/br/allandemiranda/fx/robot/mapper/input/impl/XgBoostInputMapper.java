package br.allandemiranda.fx.robot.mapper.input.impl;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.XgBoostInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.XgBoostInputCreateDto;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.input.impl.XgBoostInputEntry;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class XgBoostInputMapper implements InputMapper<XgBoostInputEntry, XgBoostInputDto, XgBoostInputCreateDto> {

  @Override
  public XgBoostInputDto toDto(XgBoostInputEntry xgBoostInput) {
    return new XgBoostInputDto(
        xgBoostInput.id(),
        xgBoostInput.eaName(),
        xgBoostInput.maxDepth(),
        xgBoostInput.eta(),
        xgBoostInput.subsample(),
        xgBoostInput.colSampleByTree(),
        xgBoostInput.minChildWeight(),
        xgBoostInput.lambda(),
        xgBoostInput.alpha(),
        xgBoostInput.rounds(),
        xgBoostInput.earlyStoppingRounds(),
        xgBoostInput.horizon(),
        xgBoostInput.minimalLevelAccepted(),
        xgBoostInput.validationPercentage()
    );
  }

  @Override
  public XgBoostInputEntry toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, XgBoostInputCreateDto xgBoostInputCreateDto) {
    return new XgBoostInputEntry(
        id,
        expertAdvisorDto.eaName(),
        xgBoostInputCreateDto.maxDepth(),
        xgBoostInputCreateDto.eta(),
        xgBoostInputCreateDto.subsample(),
        xgBoostInputCreateDto.colSampleByTree(),
        xgBoostInputCreateDto.minChildWeight(),
        xgBoostInputCreateDto.lambda(),
        xgBoostInputCreateDto.alpha(),
        xgBoostInputCreateDto.rounds(),
        xgBoostInputCreateDto.earlyStoppingRounds(),
        xgBoostInputCreateDto.horizon(),
        xgBoostInputCreateDto.minimalLevelAccepted(),
        xgBoostInputCreateDto.validationPercentage()
    );
  }
}
