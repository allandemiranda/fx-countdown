package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.IMASlowDto;
import br.allandemiranda.fx.robot.mapper.IMASlowMapper;
import br.allandemiranda.fx.robot.model.IMASlow;
import br.allandemiranda.fx.robot.repository.IMASlowRepository;
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
public class IMASlowService {

  private final IMASlowRepository repository;

  @Transactional(readOnly = true)
  public Mono<IMASlowDto> getIMASlow(@NonNull ChartDto chartDto) {
    return this.getRepository().findIMASlow(chartDto.id()).map(iMASlow -> IMASlowMapper.toIMASlowDto(chartDto, iMASlow));
  }

  public Mono<IMASlowDto> createIMASlow(@NonNull ChartDto chartDto, @NonNull IMASlowCreateDto iMASlowCreateDto) {
    IMASlow model = IMASlowMapper.toIMASlow(chartDto, iMASlowCreateDto);
    return this.getRepository().save(model).map(iMASlow -> IMASlowMapper.toIMASlowDto(chartDto, iMASlow));
  }

  public Mono<Void> deleteIMASlow(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIMASlow(chartDto.id());
  }

}
