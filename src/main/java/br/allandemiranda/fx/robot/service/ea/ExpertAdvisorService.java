package br.allandemiranda.fx.robot.service.ea;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.ea.create.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.ea.update.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.mapper.ea.ExpertAdvisorMapper;
import br.allandemiranda.fx.robot.model.ea.impl.ExpertAdvisorEntry;
import br.allandemiranda.fx.robot.repository.ea.ExpertAdvisorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Service
public class ExpertAdvisorService {

  private final ExpertAdvisorMapper mapper;
  private final ExpertAdvisorRepository repository;

  public Mono<ExpertAdvisorDto> create(String symbolName, Timeframe timeframe, ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    log.debug("Create an ExpertAdvisor [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto);
    return this.get(symbolName, timeframe, expertAdvisorCreateDto.eaName()).switchIfEmpty(Mono.defer(() -> {
      ExpertAdvisorEntry model = this.getMapper().toModel(symbolName, timeframe, expertAdvisorCreateDto);
      return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(expertAdvisor));
    })).or(Mono.empty());
  }

  public Mono<Void> delete(ExpertAdvisorDto expertAdvisorDto) {
    log.debug("Delete an ExpertAdvisor [expertAdvisorDto={}]", expertAdvisorDto);
    return this.getRepository().deleteById(expertAdvisorDto.eaName());
  }

  public Mono<ExpertAdvisorDto> get(String symbolName, Timeframe timeframe, String eaName) {
    log.debug("Get ExpertAdvisor by [symbolName={}, timeframe={}, eaName={}]", symbolName, timeframe, eaName);
    return this.getRepository().findFirstByEaNameAndSymbolNameAndTimeframe(eaName, symbolName, timeframe).map(expertAdvisor -> this.getMapper().toDto(expertAdvisor));
  }

  public Flux<ExpertAdvisorDto> get(String symbolName, Timeframe timeframe) {
    log.debug("Get an ExpertAdvisor by [symbolName={}, timeframe={}]", symbolName, timeframe);
    return this.getRepository().findBySymbolNameAndTimeframe(symbolName, timeframe).map(expertAdvisor -> this.getMapper().toDto(expertAdvisor));
  }

  public Mono<ExpertAdvisorDto> update(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    log.debug("Update an ExpertAdvisor [expertAdvisorDto={}, expertAdvisorUpdateDto={}]", expertAdvisorDto, expertAdvisorUpdateDto);
    ExpertAdvisorEntry model = this.getMapper().toModel(expertAdvisorDto, expertAdvisorUpdateDto);
    return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(expertAdvisor));
  }

  public Mono<ExpertAdvisorDto> updateStatus(ExpertAdvisorDto expertAdvisorDto, EAStatus status) {
    log.debug("Update an ExpertAdvisor Status [expertAdvisorDto={}, status={}]", expertAdvisorDto, status);
    ExpertAdvisorEntry model = this.getMapper().toModel(expertAdvisorDto, status);
    return this.getRepository().save(model).map(expertAdvisor -> this.getMapper().toDto(expertAdvisor));
  }
}
