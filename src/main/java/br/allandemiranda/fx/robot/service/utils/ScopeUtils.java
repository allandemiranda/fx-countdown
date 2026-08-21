package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.model.input.ScopeInput;
import br.allandemiranda.fx.robot.model.provider.Timeseries;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import reactor.util.function.Tuple2;

@NullMarked
@Slf4j
@UtilityClass
public class ScopeUtils {

  @Contract(pure = true)
  private static ScopeInput combineScopes(ScopeInput current, ScopeInput next) {
    log.trace("combineScopes: current={}, next={}", current, next);
    OffsetDateTime newStart = current.startScope().isBefore(next.startScope()) ? next.startScope() : current.startScope();
    OffsetDateTime newEnd = current.endScope().isAfter(next.endScope()) ? next.endScope() : current.endScope();

    ScopeInput scopeInput = new ScopeInput() {
      @Override
      public OffsetDateTime endScope() {
        return newEnd;
      }

      @Override
      public OffsetDateTime startScope() {
        return newStart;
      }
    };

    log.trace("combineScopes: ScopeInput={}", scopeInput);
    return scopeInput;
  }

  @Contract(pure = true)
  public static Function<@Unmodifiable Collection<ScopeInput>, ScopeInput> calculateMergedScope() {
    return scopes -> {
      log.trace("calculateMergedScope: scopes.size={}", scopes.size());
      return scopes.stream().reduce(ScopeUtils::combineScopes).orElseThrow(IllegalStateException::new);
    };
  }

  @Contract(pure = true)
  public static ScopeInput getScopeInputByTimeseries(Tuple2<? extends Timeseries, ? extends Timeseries> scopeTimeseries) {
    return new ScopeInput() {
      @Override
      public OffsetDateTime endScope() {
        return scopeTimeseries.getT2().timestamp();
      }

      @Override
      public OffsetDateTime startScope() {
        return scopeTimeseries.getT1().timestamp();
      }
    };
  }
}
