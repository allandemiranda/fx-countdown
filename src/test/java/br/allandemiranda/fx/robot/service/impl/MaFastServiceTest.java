package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MaFastMapper;
import br.allandemiranda.fx.robot.model.impl.MaFast;
import br.allandemiranda.fx.robot.repository.impl.MaFastRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaFastServiceTest extends ChartObjectServiceTest<MaFast, MaFastDto, MaFastCreateDto> {

  @Mock
  @Getter
  private MaFast model;

  @Mock
  @Getter
  private MaFastCreateDto createDto;

  @Mock
  @Getter
  private MaFastRepository repository;

  @Spy
  @Getter
  private MaFastMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private MaFastService service;

}