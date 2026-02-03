package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ADXDto;
import br.allandemiranda.fx.robot.dto.impl.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.ADXMapper;
import br.allandemiranda.fx.robot.model.impl.ADX;
import br.allandemiranda.fx.robot.repository.impl.ADXRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ADXServiceTest extends ChartObjectServiceTest<ADX, ADXDto, ADXCreateDto> {

  @Mock
  @Getter
  private ADX model;

  @Mock
  @Getter
  private ADXCreateDto createDto;

  @Mock
  @Getter
  private ADXRepository repository;

  @Spy
  @Getter
  private ADXMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private ADXService service;

}