package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.IRSIDto;
import br.allandemiranda.fx.robot.mapper.IRSIMapper;
import br.allandemiranda.fx.robot.model.IRSI;
import br.allandemiranda.fx.robot.repository.IRSIRepository;
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
public class IRSIService {

  private final IRSIRepository repository;

  @Transactional(readOnly = true)
  public Mono<IRSIDto> getIRSI(@NonNull ChartDto chartDto) {
    return this.getRepository().findIRSI(chartDto.id()).map(iRSI -> IRSIMapper.toIRSIDto(chartDto, iRSI));
  }

  public Mono<IRSIDto> createIRSI(@NonNull ChartDto chartDto, @NonNull IRSICreateDto iRSICreateDto) {
    IRSI model = IRSIMapper.toIRSI(chartDto, iRSICreateDto);
    return this.getRepository().save(model).map(iRSI -> IRSIMapper.toIRSIDto(chartDto, iRSI));
  }

  public Mono<Void> deleteIRSI(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIRSI(chartDto.id());
  }

}
