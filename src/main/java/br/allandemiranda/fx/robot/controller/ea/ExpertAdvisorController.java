package br.allandemiranda.fx.robot.controller.ea;

import br.allandemiranda.fx.robot.annotation.field.EaName;
import br.allandemiranda.fx.robot.annotation.field.SymbolName;
import br.allandemiranda.fx.robot.controller.util.ExpertAdvisorUtils;
import br.allandemiranda.fx.robot.controller.util.SymbolUtils;
import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.ea.create.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.ea.update.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.dto.input.ScopeInputDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.exception.impl.ExpertAdvisorConflictException;
import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.service.core.SymbolService;
import br.allandemiranda.fx.robot.service.core.TickService;
import br.allandemiranda.fx.robot.service.ea.ExpertAdvisorService;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import br.allandemiranda.fx.robot.service.input.InputService;
import br.allandemiranda.fx.robot.service.input.impl.ScopeInputService;
import br.allandemiranda.fx.robot.service.utils.ScopeUtils;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@AllArgsConstructor
@Getter
@Validated
@RestController
@RequestMapping("symbols/{symbolName}/chart/{timeframe}/expert_advisors")
public class ExpertAdvisorController {

  private final ExpertAdvisorService expertAdvisorService;
  private final List<IndicatorService<?, ?, ?>> indicatorServices;
  private final List<InputService<?, ?, ?>> inputServices;
  private final ScopeInputService scopeService;
  private final SymbolService symbolService;
  private final TickService tickService;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(path = "/{name}/clean_build", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> cleanInputAndIndicatorData(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName String name) {
    log.info("Clean Input and Indicator data for EA [eaName={}, symbolName={}, timeframe={}]", name, symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> Flux.fromStream(this.getIndicatorServices().stream().map(indicatorService -> indicatorService.delete(expertAdvisorDto))).then())
        .doOnError(throwable -> log.warn("Trouble cleaning indicator data for EA [eaName={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> create(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @RequestBody @Valid ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    log.debug("Create an EA [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto);

    Function<ExpertAdvisorDto, Mono<ExpertAdvisorDto>> createInputsFunction = expertAdvisorDto -> Flux.fromIterable(this.getInputServices())
        .flatMap(inputService -> inputService.create(expertAdvisorDto, expertAdvisorCreateDto))
        .doOnError(throwable -> log.error("Trouble for creating input for the expert advisor [expertAdvisorDto={}]", expertAdvisorDto, throwable))
        .switchIfEmpty(Mono.defer(() -> {
          log.error("Error creating a input for the expert advisor: create generated empty input [expertAdvisorDto={}]", expertAdvisorDto);
          return Mono.error(IllegalStateException::new);
        }))
        .collectList()
        .flatMap(list -> {
          log.trace("{} Input create processed in for [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", list.size(), symbolName, timeframe, expertAdvisorCreateDto);
          return Mono.just(expertAdvisorDto);
        });

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> this.getExpertAdvisorService().create(symbolDto.symbolName(), timeframe, expertAdvisorCreateDto))
        .switchIfEmpty(Mono.defer(() -> {
          log.debug("ExpertAdvisor to create already exist [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto);
          return Mono.error(() -> new ExpertAdvisorConflictException(expertAdvisorCreateDto.eaName() + " already exist"));
        }))
        .flatMap(createInputsFunction)
        .onErrorResume(throwable -> {
          log.error("Trouble for creating input for the expert advisor [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto, throwable);
          log.warn("Deleting all data created for [symbolName={}, timeframe={}, expertAdvisorCreateDto={}]", symbolName, timeframe, expertAdvisorCreateDto);

          return ExpertAdvisorUtils.deleteEaAndAllInputAndAllIndicator(symbolName, timeframe, expertAdvisorCreateDto.eaName(), this.getSymbolService(), this.getExpertAdvisorService(), this.getIndicatorServices(),
                  this.getInputServices())
              .then(Mono.error(IllegalStateException::new));
        });
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<Void> delete(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String name) {
    log.info("Delete an EA [eaName={}, symbolName={}, timeframe={}]", name, symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> ExpertAdvisorUtils.deleteEaAndAllInputAndAllIndicator(expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), expertAdvisorDto.eaName(), this.getSymbolService(),
            this.getExpertAdvisorService(), this.getIndicatorServices(), this.getInputServices()).then(this.getExpertAdvisorService().delete(expertAdvisorDto)))
        .doOnError(throwable -> log.warn("Trouble deleting EA [eaName={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> find(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String name) {
    log.debug("Find a EA [eaName={}, eaName={}, timeframe={}]", name, symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .doOnError(throwable -> log.warn("Trouble for finding ExpertAdvisor [eaName={}]", name, throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<ExpertAdvisorDto> findAll(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe) {
    log.debug("Find All EAs per Chart [symbolName={}, timeframe={}]", symbolName, timeframe);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService()).flatMapMany(symbolDto -> this.getExpertAdvisorService().get(symbolDto.symbolName(), timeframe));
  }

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ExpertAdvisorDto> update(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName @Valid String name,
      @RequestBody @Valid ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    log.debug("Update an EA [eaName={}, symbolName={}, timeframe={}, expertAdvisorUpdateDto={}]", name, symbolName, timeframe, expertAdvisorUpdateDto);
    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> this.getExpertAdvisorService().update(expertAdvisorDto, expertAdvisorUpdateDto))
        .doOnError(throwable -> log.warn("Trouble for update ExpertAdvisor [eaName={}, symbolName={}, timeframe={}]", name, symbolName, timeframe, throwable));
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(path = "/{name}/validate_scope", produces = MediaType.APPLICATION_JSON_VALUE)
  public Mono<ScopeInputDto> validateScope(@PathVariable @SymbolName @Valid String symbolName, @PathVariable @Valid Timeframe timeframe, @PathVariable @EaName String name,
      @RequestBody(required = false) @Valid ScopeInput scopeInput) {
    log.info("Validate scope [symbolName={}, timeframe={}, eaName={}, scopeInput={}]", symbolName, timeframe, name, scopeInput);

    return SymbolUtils.getSymbol(symbolName, this.getSymbolService())
        .flatMap(symbolDto -> ExpertAdvisorUtils.getExpertAdvisor(symbolDto, timeframe, name, this.getExpertAdvisorService()))
        .flatMap(expertAdvisorDto -> {
          EAStatus currentStatus = expertAdvisorDto.status();
          if (!EAStatus.CREATED.equals(currentStatus) && EAStatus.READY_TO_USE.equals(currentStatus)) {
            log.warn("Cannot validate scope for EA [eaName={}] in status [{}]", name, currentStatus);
            return Mono.error(new ExpertAdvisorConflictException("ExpertAdvisor " + name + " is currently in status " + currentStatus + " and cannot validate scope"));
          }

          Mono<ExpertAdvisorDto> advisorDto = this.getExpertAdvisorService().updateStatus(expertAdvisorDto, EAStatus.VALIDATING_SCOPE);
          Mono<ScopeInputDto> scope = this.getScopeService().get(expertAdvisorDto);
          Mono<ScopeInput> tickScope = this.getTickService().getScope(symbolName);
          Mono<List<ScopeInput>> listOfScopes = Flux.fromIterable(this.getIndicatorServices()).flatMap(indicatorService -> indicatorService.getScope(expertAdvisorDto)).collectList();
          return Mono.zip(advisorDto, scope, tickScope, listOfScopes);
        })
        .flatMap(objects -> {
          ExpertAdvisorDto expertAdvisorDto = objects.getT1();

          ScopeInputDto oldScopeInput = objects.getT2();

          ScopeInput tickScopeInput = objects.getT3();
          List<ScopeInput> scopeInputs = Arrays.asList(oldScopeInput, tickScopeInput);
          List<ScopeInput> indicatorScopes = objects.getT4();
          scopeInputs.addAll(indicatorScopes);

          ScopeInput newScopeInput = ScopeUtils.calculateMergedScope().apply(scopeInputs);
          return this.getScopeService().create(expertAdvisorDto, new ScopeInputCreateDto(newScopeInput.startScope(), newScopeInput.endScope()));
        });
  }
}
