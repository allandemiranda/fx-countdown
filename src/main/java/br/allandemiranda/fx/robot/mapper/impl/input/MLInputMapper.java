package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.MLInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.MLInputDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.MLInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class MLInputMapper implements InputDashboardMapper<MLInput, MLInputDto, MLInputCreateDto> {

  public MLInputDto toDto(DashboardDto dashboardDto, MLInput mlInput) {
    return new MLInputDto(mlInput.id(), dashboardDto, mlInput.chartObjectNum(), mlInput.maxDepth(), mlInput.eta(), mlInput.subsample(), mlInput.colSampleByTree(), mlInput.minChildWeight(), mlInput.lambda(), mlInput.alpha());
  }

  public MLInput toModel(UUID id, DashboardDto dashboardDto, MLInputCreateDto mlInputCreateDto) {
    return new MLInput(id, dashboardDto.id(), mlInputCreateDto.chartObjectNum(), mlInputCreateDto.maxDepth(), mlInputCreateDto.eta(), mlInputCreateDto.subsample(), mlInputCreateDto.colSampleByTree(),
        mlInputCreateDto.minChildWeight(), mlInputCreateDto.lambda(), mlInputCreateDto.alpha());
  }
}
