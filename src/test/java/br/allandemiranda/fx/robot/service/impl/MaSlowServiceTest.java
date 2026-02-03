package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MaSlowMapper;
import br.allandemiranda.fx.robot.model.impl.MaSlow;
import br.allandemiranda.fx.robot.repository.impl.MaSlowRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaSlowServiceTest extends ChartObjectServiceTest<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @Mock
  @Getter
  private MaSlow model;

  @Mock
  @Getter
  private MaSlowCreateDto createDto;

  @Mock
  @Getter
  private MaSlowRepository repository;

  @Spy
  @Getter
  private MaSlowMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private MaSlowService service;

}