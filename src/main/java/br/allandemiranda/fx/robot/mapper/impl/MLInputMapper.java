package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.MLInput;
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
