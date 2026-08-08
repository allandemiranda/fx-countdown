package br.allandemiranda.fx.robot.mapper.impl.indicator;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.mapper.IndicatorMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.ADX;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class ADXMapper implements IndicatorMapper<ADX, ADXDto, ADXCreateDto> {

  @Override
  public ADXDto toDto(DashboardDto dashboardDto, ADX model) {
    return new ADXDto(model.id(), dashboardDto, model.timestamp(), model.mainLine(), model.plusDiLine(), model.minusDiLine());
  }

  @Override
  public ADX toModel(UUID id, DashboardDto dashboardDto, ADXCreateDto createDto) {
    return new ADX(id, dashboardDto.id(), createDto.timestamp(), createDto.mainLine(), createDto.plusDiLine(), createDto.minusDiLine());
  }
}
