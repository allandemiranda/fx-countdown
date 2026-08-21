package br.allandemiranda.fx.robot.dto.ea.update;

import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorParameters;
import java.io.Serializable;

public record ExpertAdvisorUpdateDto(String description) implements Serializable, ExpertAdvisorParameters {

}
