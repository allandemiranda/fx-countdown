package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.IATRDto;
import br.allandemiranda.fx.robot.mapper.IATRMapper;
import br.allandemiranda.fx.robot.model.IATR;
import br.allandemiranda.fx.robot.repository.IATRRepository;
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
public class IATRService {

  private final IATRRepository repository;

  @Transactional(readOnly = true)
  public Mono<IATRDto> getIATR(@NonNull ChartDto chartDto) {
    return this.getRepository().findIATR(chartDto.id()).map(iATR -> IATRMapper.toIATRDto(chartDto, iATR));
  }

  public Mono<IATRDto> createIATR(@NonNull ChartDto chartDto, @NonNull IATRCreateDto iATRCreateDto) {
    IATR model = IATRMapper.toIATR(chartDto, iATRCreateDto);
    return this.getRepository().save(model).map(iATR -> IATRMapper.toIATRDto(chartDto, iATR));
  }

  public Mono<Void> deleteIATR(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIATR(chartDto.id());
  }

}
