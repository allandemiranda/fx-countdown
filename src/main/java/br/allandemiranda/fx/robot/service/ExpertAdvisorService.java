package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.enums.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.ConflictException;
import br.allandemiranda.fx.robot.mapper.ExpertAdvisorMapper;
import br.allandemiranda.fx.robot.model.ExpertAdvisor;
import br.allandemiranda.fx.robot.repository.ExpertAdvisorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Getter
@Service
public class ExpertAdvisorService {

  private final ExpertAdvisorRepository repository;
  private final ExpertAdvisorMapper mapper;

  public Mono<ExpertAdvisorDto> get(SymbolDto symbolDto, Timeframe timeframe, String name) {
    log.debug("Get [symbolDto={}, timeframe={}, name={}]", symbolDto, timeframe, name);
    return this.getRepository().findFirstByNameAndSymbolNameAndTimeframe(name, symbolDto.name(), timeframe).map(expertAdvisor -> this.getMapper().toDto(symbolDto, expertAdvisor));
  }

  public Flux<ExpertAdvisorDto> get(SymbolDto symbolDto, Timeframe timeframe) {
    log.debug("Get [symbolDto={}, timeframe={}]", symbolDto, timeframe);
    return this.getRepository().findBySymbolNameAndTimeframe(symbolDto.name(), timeframe).map(expertAdvisor -> this.getMapper().toDto(symbolDto, expertAdvisor));
  }

  public Mono<ExpertAdvisorDto> create(SymbolDto symbolDto, Timeframe timeframe, ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    log.debug("Create [expertAdvisorCreateDto={}]", expertAdvisorCreateDto);
    return this.get(symbolDto, timeframe, expertAdvisorCreateDto.name()).flatMap(expertAdvisorDto -> {
      log.debug("Create [expertAdvisorCreateDto={}] already exist [expertAdvisorDto{}]", expertAdvisorCreateDto, expertAdvisorDto);
      return Mono.<ExpertAdvisorDto>error(() -> new ConflictException(expertAdvisorCreateDto.name() + " for Symbol=" + expertAdvisorCreateDto.name() + ", and Timeframe=" + timeframe + " already exist"));
    }).switchIfEmpty(Mono.defer(() -> {
      ExpertAdvisor model = this.getMapper().toModel(symbolDto, timeframe, expertAdvisorCreateDto);
      log.trace("Create [expertAdvisorCreateDto={}], new object generated to save [dashboard={}]", expertAdvisorCreateDto, model);
      return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(symbolDto, expertAdvisor));
    }));
  }

  public Mono<ExpertAdvisorDto> update(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    log.debug("Update [expertAdvisorDto={}, expertAdvisorUpdateDto={}]", expertAdvisorDto, expertAdvisorUpdateDto);
    ExpertAdvisor model = this.getMapper().toModel(expertAdvisorDto, expertAdvisorUpdateDto);
    log.trace("Update [expertAdvisorDto={}, expertAdvisorUpdateDto={}], update object generated to save [dashboard={}]", expertAdvisorDto, expertAdvisorUpdateDto, model);
    return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(expertAdvisorDto.symbolDto(), expertAdvisor));
  }

  public Mono<ExpertAdvisorDto> updateStatus(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorStatus status) {
    log.debug("Update Status [expertAdvisorDto={}, status={}]", expertAdvisorDto, status);
    ExpertAdvisor model = this.getMapper().toModel(expertAdvisorDto, status);
    log.trace("Update Status [expertAdvisorDto={}, status={}], update object generated to save [dashboard={}]", expertAdvisorDto, status, model);
    return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(expertAdvisorDto.symbolDto(), expertAdvisor));
  }

  public Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    log.debug("Delete [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteById(expertAdvisorDto.id());
  }
}
