package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.MaSlowMapper;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.repository.MaSlowRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaSlowServiceTest extends AbstractChartObjectServiceTest<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @Mock
  @Getter
  private MaSlow model;

  @Mock
  @Getter
  private MaSlowRepository repository;

  @Spy
  @Getter
  private MaSlowMapper mapper;

  @InjectMocks
  @Getter
  private MaSlowService service;

}