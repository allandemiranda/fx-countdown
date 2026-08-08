package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.InputCreateDto;
import br.allandemiranda.fx.robot.dto.InputDto;
import br.allandemiranda.fx.robot.model.InputModel;
import java.util.UUID;

public interface InputMapper<M extends InputModel, D extends InputDto, C extends InputCreateDto> {

  D toDto(ExpertAdvisorDto expertAdvisorDto, M model);

  M toModel(UUID id, ExpertAdvisorDto expertAdvisorDto, C createDto);

}
