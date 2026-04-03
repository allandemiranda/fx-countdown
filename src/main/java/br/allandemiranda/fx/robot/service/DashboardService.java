package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class DashboardService implements InputObjectService<Dashboard, DashboardDto, DashboardCreateDto> {

  private final DashboardRepository repository;
  private final DashboardMapper mapper;

}
