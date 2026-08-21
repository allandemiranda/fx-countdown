package br.allandemiranda.fx.robot.service.utils;

import br.allandemiranda.fx.robot.service.input.InputService;
import java.util.Collection;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import reactor.core.publisher.Mono;

@NullMarked
@Slf4j
@UtilityClass
public class InputUtils {

  @Contract(pure = true)
  public static <I extends InputService> @Unmodifiable Collection<Mono<?>> doActionForAllParallel(@Unmodifiable Collection<I> inputs, Function<I, Mono<?>> action) {
    return inputs.parallelStream().map(action).toList();
  }
}
