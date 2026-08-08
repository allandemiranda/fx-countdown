package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorCreateDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorDto;
import br.allandemiranda.fx.robot.dto.core.ExpertAdvisorUpdateDto;
import br.allandemiranda.fx.robot.dto.core.SymbolDto;
import br.allandemiranda.fx.robot.enums.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ExpertAdvisor;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ExpertAdvisorMapper {

  public ExpertAdvisor toModel(SymbolDto symbolDto, Timeframe timeframe, ExpertAdvisorCreateDto expertAdvisorCreateDto) {
    return new ExpertAdvisor(UUID.randomUUID(), expertAdvisorCreateDto.name(), symbolDto.name(), timeframe, LocalDateTime.now(ZoneId.systemDefault()), ExpertAdvisorStatus.CREATED, expertAdvisorCreateDto.description());
  }

  public ExpertAdvisor toModel(ExpertAdvisorDto expertAdvisorDto) {
    return new ExpertAdvisor(expertAdvisorDto.id(), expertAdvisorDto.name(), expertAdvisorDto.symbolDto().name(), expertAdvisorDto.timeframe(), expertAdvisorDto.updateTime(), expertAdvisorDto.status(),
        expertAdvisorDto.description());
  }

  public ExpertAdvisor toModel(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorStatus status) {
    ExpertAdvisorDto.ExpertAdvisorDtoBuilder builder = expertAdvisorDto.toBuilder();
    builder.updateTime(LocalDateTime.now(ZoneId.systemDefault()));
    builder.status(status);
    return this.toModel(builder.build());
  }

  public ExpertAdvisor toModel(ExpertAdvisorDto expertAdvisorDto, ExpertAdvisorUpdateDto expertAdvisorUpdateDto) {
    ExpertAdvisorDto.ExpertAdvisorDtoBuilder builder = expertAdvisorDto.toBuilder();
    builder.updateTime(LocalDateTime.now(ZoneId.systemDefault()));

    Optional.ofNullable(expertAdvisorUpdateDto.description()).ifPresent(builder::description);

    return this.toModel(builder.build());
  }

  public ExpertAdvisorDto toDto(SymbolDto symbolDto, ExpertAdvisor expertAdvisor) {
    return new ExpertAdvisorDto(expertAdvisor.id(), expertAdvisor.name(), symbolDto, expertAdvisor.timeframe(), expertAdvisor.status(), expertAdvisor.updateTime(), expertAdvisor.description());
  }
}
