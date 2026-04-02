package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.MLInput;
import org.springframework.stereotype.Component;

@Component
public final class MLInputMapper implements InputObjectMapper<MLInput, MLInputDto, MLInputCreateDto> {

  public MLInputDto toDto(ChartDto chartDto, MLInput mlInput) {
    return new MLInputDto(chartDto, mlInput.chartObjectNum(), mlInput.maxDepth(), mlInput.eta(), mlInput.subsample(), mlInput.colSampleByTree(), mlInput.minChildWeight(), mlInput.lambda(), mlInput.alpha());
  }

  public MLInput toModel(ChartDto chartDto, MLInputCreateDto mlInputCreateDto) {
    return new MLInput(chartDto.id(), mlInputCreateDto.chartObjectNum(), mlInputCreateDto.maxDepth(), mlInputCreateDto.eta(), mlInputCreateDto.subsample(), mlInputCreateDto.colSampleByTree(), mlInputCreateDto.minChildWeight(), mlInputCreateDto.lambda(), mlInputCreateDto.alpha());
  }
}
