package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.InputCreateDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.mapper.InputMapper;
import br.allandemiranda.fx.robot.model.InputModel;
import br.allandemiranda.fx.robot.repository.InputRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public interface InputService<M extends InputModel, D extends InputDto, C extends InputCreateDto> {

  InputRepository<M> getRepository();

  InputMapper<M, D, C> getMapper();

  private Logger log() {
    return LoggerFactory.getLogger(getClass());
  }

  default Mono<D> get(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Get [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().findByExpertAdvisorId(expertAdvisorDto.id()).map(model -> this.getMapper().toDto(expertAdvisorDto, model));
  }

  default Mono<D> create(ExpertAdvisorDto expertAdvisorDto, C createDto) {
    this.log().debug("Create [expertAdvisorDto={}, createDto={}]", expertAdvisorDto, createDto);
    return this.get(expertAdvisorDto).flatMap(inputDashboardDto -> {
      this.log().trace("Create [expertAdvisorDto={}, createDto={}], object already exist [inputDashboardDto={}]", expertAdvisorDto, createDto, inputDashboardDto);
      M model = this.getMapper().toModel(inputDashboardDto.id(), expertAdvisorDto, createDto);
      return this.getRepository().save(model).map(inputDashboard -> this.getMapper().toDto(expertAdvisorDto, inputDashboard));
    }).switchIfEmpty(Mono.defer(() -> {
      M model = this.getMapper().toModel(UUID.randomUUID(), expertAdvisorDto, createDto);
      this.log().trace("Create [expertAdvisorDto={}, createDto={}], new object generated to save [inputDashboard={}]", expertAdvisorDto, createDto, model);
      return this.getRepository().save(model).map(inputDashboard -> this.getMapper().toDto(expertAdvisorDto, inputDashboard));
    }));
  }

  default Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    this.log().debug("Delete [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteByExpertAdvisorId(expertAdvisorDto.id());
  }
}
