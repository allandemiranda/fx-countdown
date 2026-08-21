package br.allandemiranda.fx.robot.mapper.ea;

import br.allandemiranda.fx.robot.dto.ea.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.ea.create.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.ea.update.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ea.impl.ExpertAdvisorEntry;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Component;

@NullMarked
@Component
public final class ExpertAdvisorMapper {

  @Contract("_ -> new")
  public ExpertAdvisorDto toDto(ExpertAdvisorEntry expertAdvisorEntry) {
    return new ExpertAdvisorDto(expertAdvisorEntry.eaName(), expertAdvisorEntry.symbolName(), expertAdvisorEntry.timeframe(), expertAdvisorEntry.status(), expertAdvisorEntry.updateTime(), expertAdvisorEntry.description());
  }

  @Contract("_, _ -> new")
  public ExpertAdvisorEntry toModel(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    return new ExpertAdvisorEntry(expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), OffsetDateTime.now(ZoneId.systemDefault()), expertAdvisorDto.status(),
        expertAdvisorUpdateDto.description());
  }

  @Contract("_, _ -> new")
  public ExpertAdvisorEntry toModel(ExpertAdvisorDto expertAdvisorDto, EAStatus eaStatus) {
    return new ExpertAdvisorEntry(expertAdvisorDto.eaName(), expertAdvisorDto.symbolName(), expertAdvisorDto.timeframe(), OffsetDateTime.now(ZoneId.systemDefault()), eaStatus, expertAdvisorDto.description());
  }

  @Contract("_, _, _ -> new")
  public ExpertAdvisorEntry toModel(String symbolName, Timeframe timeframe, ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    return new ExpertAdvisorEntry(expertAdvisorCreateDto.eaName(), symbolName, timeframe, OffsetDateTime.now(ZoneId.systemDefault()), EAStatus.CREATED, expertAdvisorCreateDto.description());
  }
}
