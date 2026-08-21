package br.allandemiranda.fx.robot.service.input;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.mapper.input.InputMapper;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import br.allandemiranda.fx.robot.repository.input.InputRepository;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@NullMarked
public interface InputService<M extends Input, D extends Input, C extends InputCreate> {

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> create(ExpertAdvisorDto expertAdvisorDto, C inputCreate) {
    this.log().debug("Create an Input [expertAdvisorDto={}, inputCreate={}]", expertAdvisorDto, inputCreate);
    return this.get(expertAdvisorDto).flatMap(input -> {
      M model = this.getMapper().toModel(input.id(), expertAdvisorDto, inputCreate);
      this.log().trace("Create an Input [expertAdvisorDto={}, inputCreate={}], updating already exist [input={}]", expertAdvisorDto, inputCreate, input);
      return this.getRepository().save(model).map(entry -> this.getMapper().toDto(entry));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), expertAdvisorDto, inputCreate);
      return this.getRepository().save(model).map(entry -> this.getMapper().toDto(entry));
    }));
  }

  default Mono<D> create(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorInputs expertAdvisorInputs) {
    return this.create(expertAdvisorDto, this.getInputCreateFromExpertAdvisorParameters(expertAdvisorInputs));
  }

  default Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Delete All Input by [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteByEaName(expertAdvisorDto.eaName());
  }

  default Mono<D> get(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Get an Input by [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().findByEaName(expertAdvisorDto.eaName()).map(entry -> this.getMapper().toDto(entry));
  }

  C getInputCreateFromExpertAdvisorParameters(ExpertAdvisorInputs expertAdvisorInputs);

  @Contract(pure = true)
  InputMapper<M, D, C> getMapper();

  @Contract(pure = true)
  InputRepository<M> getRepository();
}
