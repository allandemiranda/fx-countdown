package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import java.time.OffsetDateTime;
import java.util.Collection;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@UtilityClass
public class ScopeUtils {

  public static ScopeInputCreateDto combineScopes(ScopeInputCreateDto current, ScopeInputCreateDto next) {
    ScopeUtils.log.trace("combineScopes: current={}, next={}", current, next);
    OffsetDateTime newStart = current.startScope().isBefore(next.startScope()) ? next.startScope() : current.startScope();
    OffsetDateTime newEnd = current.endScope().isAfter(next.endScope()) ? next.endScope() : current.endScope();

    ScopeInputCreateDto scopeInputCreateDto = new ScopeInputCreateDto(newStart, newEnd);
    ScopeUtils.log.trace("combineScopes: scopeInputCreateDto={}", scopeInputCreateDto);
    return scopeInputCreateDto;
  }

  public Mono<ScopeInputCreateDto> calculateMergedScope(Collection<Mono<ScopeInputCreateDto>> scopePublishers) {
    ScopeUtils.log.trace("calculateMergedScope: scopePublishers.size={}", scopePublishers.size());
    return Flux.merge(scopePublishers).reduce(ScopeUtils::combineScopes).map(finalScope -> new ScopeInputCreateDto(finalScope.startScope(), finalScope.endScope()));
  }
}
