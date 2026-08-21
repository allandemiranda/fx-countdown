package br.allandemiranda.fx.robot.service.indicator;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.mapper.indicator.IndicatorMapper;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.repository.indicator.IndicatorRepository;
import br.allandemiranda.fx.robot.service.utils.ScopeUtils;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@NullMarked
public interface IndicatorService<M extends Indicator, D extends Indicator, C extends IndicatorCreate> {

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> create(ExpertAdvisorDto expertAdvisorDto, C createDto) {
    this.log().debug("Create an Indicator [expertAdvisorDto={}, createDto={}]", expertAdvisorDto, createDto);
    return this.get(expertAdvisorDto, createDto.timestamp()).flatMap(indicator -> {
      M model = this.getMapper().toModel(indicator.id(), expertAdvisorDto, createDto);
      this.log().trace("Create an Indicator [expertAdvisorDto={}, createDto={}], updating already exist [indicatorDto={}]", expertAdvisorDto, createDto, indicator);
      return this.getRepository().save(model).map(entry -> this.getMapper().toDto(entry));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), expertAdvisorDto, createDto);
      return this.getRepository().save(model).map(entry -> this.getMapper().toDto(entry));
    }));
  }

  default Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Delete All Indicator by [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteAllByEaName(expertAdvisorDto.eaName());
  }

  default Mono<D> get(ExpertAdvisorDto expertAdvisorDto, OffsetDateTime timestamp) {
    this.log().debug("Get and Indicator by [expertAdvisorDto={}, timestamp={}]", expertAdvisorDto, timestamp);
    return this.getRepository().findByEaNameAndTimestamp(expertAdvisorDto.eaName(), timestamp).map(model -> this.getMapper().toDto(model));
  }

  default Flux<D> get(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Get the full Indicator by [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().findAllByEaNameOrderByTimestampAsc(expertAdvisorDto.eaName()).map(model -> this.getMapper().toDto(model));
  }

  @Contract(pure = true)
  IndicatorMapper<M, D, C> getMapper();

  @Contract(pure = true)
  IndicatorRepository<M> getRepository();

  default Mono<ScopeInput> getScope(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Get Indicator scope [expertAdvisorDto={}]", expertAdvisorDto);
    return Mono.zip(this.getRepository().findFirstByEaNameOrderByTimestampAsc(expertAdvisorDto.eaName()), this.getRepository().findFirstByEaNameOrderByTimestampDesc(expertAdvisorDto.eaName()))
        .map(ScopeUtils::getScopeInputByTimeseries);
  }
}
