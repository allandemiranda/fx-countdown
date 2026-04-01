package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.IMACDDto;
import br.allandemiranda.fx.robot.mapper.IMACDMapper;
import br.allandemiranda.fx.robot.model.IMACD;
import br.allandemiranda.fx.robot.repository.IMACDRepository;
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
public class IMACDService {

  private final IMACDRepository repository;

  @Transactional(readOnly = true)
  public Mono<IMACDDto> getIMACD(@NonNull ChartDto chartDto) {
    return this.getRepository().findIMACD(chartDto.id()).map(iMACD -> IMACDMapper.toIMACDDto(chartDto, iMACD));
  }

  public Mono<IMACDDto> createIMACD(@NonNull ChartDto chartDto, @NonNull IMACDCreateDto iMACDCreateDto) {
    IMACD model = IMACDMapper.toIMACD(chartDto, iMACDCreateDto);
    return this.getRepository().save(model).map(iMACD -> IMACDMapper.toIMACDDto(chartDto, iMACD));
  }

  public Mono<Void> deleteIMACD(@NonNull ChartDto chartDto) {
    return this.getRepository().deleteIMACD(chartDto.id());
  }

}
