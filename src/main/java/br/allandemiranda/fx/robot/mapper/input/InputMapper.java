package br.allandemiranda.fx.robot.mapper.input;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.input.create.InputCreate;
import br.allandemiranda.fx.robot.model.input.provider.Input;
import java.util.UUID;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface InputMapper<M extends Input, D extends Input, C extends InputCreate> {

  @Contract("_ -> new")
  D toDto(M model);

  @Contract("_, _, _ -> new")
  M toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, C create);

}
