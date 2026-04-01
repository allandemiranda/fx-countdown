package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.MLInputCreateDto;
import br.allandemiranda.fx.robot.dto.MLInputDto;
import br.allandemiranda.fx.robot.mapper.MLInputMapper;
import br.allandemiranda.fx.robot.model.MLInput;
import br.allandemiranda.fx.robot.repository.MLInputRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter(AccessLevel.PRIVATE)
@Transactional
@Service
public class MLInputService {

  private final MLInputRepository repository;

  @Transactional(readOnly = true)
  public Mono<MLInputDto> getMLInput(@NonNull ChartDto chartDto) {
    return this.getRepository().findMLInput(chartDto.id()).map(mlInput -> MLInputMapper.toMLInputDto(chartDto, mlInput));
  }

  public Mono<MLInputDto> createMLInput(@NonNull ChartDto chartDto, @NonNull MLInputCreateDto mlInputCreateDto) {
    MLInput model = MLInputMapper.toMLInput(chartDto, mlInputCreateDto);
    return this.getRepository().save(model).map(mlInput -> MLInputMapper.toMLInputDto(chartDto, mlInput));
  }

  public Mono<Void> deleteMLInput(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteMLInput(chartDto.id());
  }

}
