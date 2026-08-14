package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import br.allandemiranda.fx.robot.repository.IndicatorRepository;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IndicatorService<M extends IndicatorModel, D extends IndicatorDto, C extends IndicatorCreateDto> {

  IndicatorRepository<M> getRepository();

  IndicatorMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(ExpertAdvisorDto expertAdvisorDto, OffsetDateTime timestamp) {
    this.log().debug("Get [expertAdvisorDto={}, timestamp={}]", expertAdvisorDto, timestamp);
    return this.getRepository().findByExpertAdvisorIdAndTimestamp(expertAdvisorDto.id(), timestamp).map(model -> this.getMapper().toDto(expertAdvisorDto, model));
  }

  default Flux<D> get(ExpertAdvisorDto expertAdvisorDto) {
    log().debug("Get [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().findAllByExpertAdvisorIdOrderByTimestampAsc(expertAdvisorDto.id()).map(model -> this.getMapper().toDto(expertAdvisorDto, model));
  }

  default Flux<D> getPreviousIndicators(ExpertAdvisorDto expertAdvisorDto, OffsetDateTime timestamp, int size) {
    return this.getRepository().findAllByExpertAdvisorIdAndTimestampLessThanEqualOrderByTimestampDesc(expertAdvisorDto.id(), timestamp, PageRequest.of(0, size))
        .collectList()
        .flatMapMany(list -> {
          list.sort(Comparator.comparing(IndicatorModel::timestamp));
          return Flux.fromIterable(list);
        }).map(model -> this.getMapper().toDto(expertAdvisorDto, model));
  }

  default Mono<D> create(ExpertAdvisorDto expertAdvisorDto, C createDto) {
    this.log().debug("Create [expertAdvisorDto={}, createDto={}]", expertAdvisorDto, createDto);
    return this.get(expertAdvisorDto, createDto.timestamp()).flatMap(indicatorDto -> {
      this.log().warn("Create [expertAdvisorDto={}, createDto={}], object already exist [indicatorDto={}]", expertAdvisorDto, createDto, indicatorDto);
      M model = this.getMapper().toModel(indicatorDto.id(), expertAdvisorDto, createDto);
      this.log().trace("Create [expertAdvisorDto={}, createDto={}], updating already exist [indicator={}]", expertAdvisorDto, createDto, model);
      return this.getRepository().save(model).map(indicator -> this.getMapper().toDto(expertAdvisorDto, indicator));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), expertAdvisorDto, createDto);
      this.log().trace("Create [expertAdvisorDto={}, createDto={}], new object generated to save [indicator={}]", expertAdvisorDto, createDto, model);
      return this.getRepository().save(model).map(indicator -> this.getMapper().toDto(expertAdvisorDto, indicator));
    }));
  }

  default Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Delete [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteAllByExpertAdvisorId(expertAdvisorDto.id());
  }

  default Mono<ScopeInputCreateDto> getScope(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Get scope [expertAdvisorDto={}]", expertAdvisorDto);
    return Mono.zip(this.getRepository().findFirstByExpertAdvisorIdOrderByTimestampAsc(expertAdvisorDto.id()), this.getRepository().findFirstByExpertAdvisorIdOrderByTimestampDesc(expertAdvisorDto.id()))
        .flatMap(objects -> Mono.just(new ScopeInputCreateDto(objects.getT1().timestamp(), objects.getT2().timestamp())));
  }
}
