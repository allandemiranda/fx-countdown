package br.allandemiranda.fx.robot.dto.ea;

import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisor;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.model.provider.Chart;
import java.io.Serializable;
import java.time.OffsetDateTime;

public record ExpertAdvisorDto(
    String eaName,
    String symbolName,
    Timeframe timeframe,
    EAStatus status,
    OffsetDateTime updateTime,
    String description
) implements Serializable, Chart, ExpertAdvisorStatus, ExpertAdvisor {

}
