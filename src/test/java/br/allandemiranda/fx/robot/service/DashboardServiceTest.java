package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.mapper.DashboardMapper;
import br.allandemiranda.fx.robot.model.Dashboard;
import br.allandemiranda.fx.robot.repository.DashboardRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest extends AbstractInputObjectServiceTest<Dashboard, DashboardDto, DashboardCreateDto> {

  @Mock
  @Getter
  private Dashboard model;

  @Mock
  @Getter
  private DashboardRepository repository;

  @Spy
  @Getter
  private DashboardMapper mapper;

  @InjectMocks
  @Getter
  private DashboardService service;
}
