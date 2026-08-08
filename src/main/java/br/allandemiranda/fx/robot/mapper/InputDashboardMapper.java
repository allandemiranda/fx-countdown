package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.InputDashboardCreateDto;
import br.allandemiranda.fx.robot.dto.InputDashboardDto;
import br.allandemiranda.fx.robot.model.InputDashboardModel;
import java.util.UUID;

public interface InputDashboardMapper<M extends InputDashboardModel, D extends InputDashboardDto, C extends InputDashboardCreateDto> {

  D toDto(DashboardDto dashboardDto, M model);

  M toModel(UUID id, DashboardDto dashboardDto, C createDto);

}
