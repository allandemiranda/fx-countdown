package br.allandemiranda.fx.robot.mapper.indicator;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.indicator.create.IndicatorCreate;
import br.allandemiranda.fx.robot.model.indicator.provider.Indicator;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface IndicatorMapper<M extends Indicator, D extends Indicator, C extends IndicatorCreate> {

  @Contract("_ -> new")
  D toDto(M model);

  @Contract("_, _, _ -> new")
  M toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, C createDto);

}
