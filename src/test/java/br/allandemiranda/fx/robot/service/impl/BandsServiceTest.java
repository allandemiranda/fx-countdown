package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.BandsMapper;
import br.allandemiranda.fx.robot.model.impl.Bands;
import br.allandemiranda.fx.robot.repository.impl.BandsRepository;
import br.allandemiranda.fx.robot.service.ChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BandsServiceTest extends ChartObjectServiceTest<Bands, BandsDto, BandsCreateDto> {

  @Mock
  @Getter
  private Bands model;

  @Mock
  @Getter
  private BandsCreateDto createDto;

  @Mock
  @Getter
  private BandsRepository repository;

  @Spy
  @Getter
  private BandsMapper mapper;

  @Spy
  @InjectMocks
  @Getter
  private BandsService service;

}