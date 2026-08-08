package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.XGBoostInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class XGBoostInputMapper implements InputDashboardMapper<XGBoostInput, XGBoostInputDto, XGBoostInputCreateDto> {

  public XGBoostInputDto toDto(DashboardDto dashboardDto, XGBoostInput xgBoostInput) {
    return new XGBoostInputDto(xgBoostInput.id(), dashboardDto, xgBoostInput.horizon(), xgBoostInput.maxDepth(), xgBoostInput.eta(), xgBoostInput.subsample(), xgBoostInput.colSampleByTree(), xgBoostInput.minChildWeight(),
        xgBoostInput.lambda(), xgBoostInput.alpha(), xgBoostInput.versionFile(), xgBoostInput.minimalLevelAccepted());
  }

  public XGBoostInput toModel(UUID id, DashboardDto dashboardDto, XGBoostInputCreateDto xgBoostInputCreateDto) {
    return new XGBoostInput(id, dashboardDto.id(), xgBoostInputCreateDto.horizon(), xgBoostInputCreateDto.maxDepth(), xgBoostInputCreateDto.eta(), xgBoostInputCreateDto.subsample(), xgBoostInputCreateDto.colSampleByTree(),
        xgBoostInputCreateDto.minChildWeight(), xgBoostInputCreateDto.lambda(), xgBoostInputCreateDto.alpha(), 0, xgBoostInputCreateDto.minimalLevelAccepted());
  }
}
