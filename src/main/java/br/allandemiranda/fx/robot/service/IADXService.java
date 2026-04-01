package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.IADXDto;
import br.allandemiranda.fx.robot.mapper.IADXMapper;
import br.allandemiranda.fx.robot.model.IADX;
import br.allandemiranda.fx.robot.repository.IADXRepository;
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
public class IADXService {

  private final IADXRepository repository;

  @Transactional(readOnly = true)
  public Mono<IADXDto> getIADX(@NonNull ChartDto chartDto) {
    return this.getRepository().findIADX(chartDto.id()).map(iADX -> IADXMapper.toIADXDto(chartDto, iADX));
  }

  public Mono<IADXDto> createIADX(@NonNull ChartDto chartDto, @NonNull IADXCreateDto iADXCreateDto) {
    IADX model = IADXMapper.toIADX(chartDto, iADXCreateDto);
    return this.getRepository().save(model).map(iADX -> IADXMapper.toIADXDto(chartDto, iADX));
  }

  public Mono<Void> deleteIADX(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIADX(chartDto.id());
  }

}
