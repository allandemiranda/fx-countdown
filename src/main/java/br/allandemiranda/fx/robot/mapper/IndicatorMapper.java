package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.IndicatorCreateDto;
import br.allandemiranda.fx.robot.dto.IndicatorDto;
import br.allandemiranda.fx.robot.model.IndicatorModel;
import java.util.UUID;

public interface IndicatorMapper<M extends IndicatorModel, D extends IndicatorDto, C extends IndicatorCreateDto> {

  D toDto(DashboardDto dashboardDto, M model);

  M toModel(UUID id, DashboardDto dashboardDto, C createDto);

}
