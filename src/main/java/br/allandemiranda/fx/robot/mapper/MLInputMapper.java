package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MLInputCreateDto;
import br.allandemiranda.fx.robot.dto.MLInputDto;
import br.allandemiranda.fx.robot.model.MLInput;
import lombok.NonNull;

public class MLInputMapper {

  public static @NonNull MLInputDto toMLInputDto(@NonNull ChartDto chartDto, @NonNull MLInput mlInput) {
    return new MLInputDto(chartDto, mlInput.chartObjectNum(), mlInput.maxDepth(), mlInput.eta(), mlInput.subsample(), mlInput.colSampleByTree(), mlInput.minChildWeight(), mlInput.lambda(), mlInput.alpha());
  }

  public static @NonNull MLInput toMLInput(@NonNull ChartDto chartDto, @NonNull MLInputCreateDto mlInputCreateDto) {
    return new MLInput(chartDto.id(), mlInputCreateDto.chartObjectNum(), mlInputCreateDto.maxDepth(), mlInputCreateDto.eta(), mlInputCreateDto.subsample(), mlInputCreateDto.colSampleByTree(), mlInputCreateDto.minChildWeight(), mlInputCreateDto.lambda(), mlInputCreateDto.alpha());
  }
}
