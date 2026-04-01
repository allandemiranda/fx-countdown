package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.IMAFastDto;
import br.allandemiranda.fx.robot.mapper.IMAFastMapper;
import br.allandemiranda.fx.robot.model.IMAFast;
import br.allandemiranda.fx.robot.repository.IMAFastRepository;
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
public class IMAFastService {

  private final IMAFastRepository repository;

  @Transactional(readOnly = true)
  public Mono<IMAFastDto> getIMAFast(@NonNull ChartDto chartDto) {
    return this.getRepository().findIMAFast(chartDto.id()).map(iMAFast -> IMAFastMapper.toIMAFastDto(chartDto, iMAFast));
  }

  public Mono<IMAFastDto> createIMAFast(@NonNull ChartDto chartDto, @NonNull IMAFastCreateDto iMAFastCreateDto) {
    IMAFast model = IMAFastMapper.toIMAFast(chartDto, iMAFastCreateDto);
    return this.getRepository().save(model).map(iMAFast -> IMAFastMapper.toIMAFastDto(chartDto, iMAFast));
  }

  public Mono<Void> deleteIMAFast(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIMAFast(chartDto.id());
  }

}
