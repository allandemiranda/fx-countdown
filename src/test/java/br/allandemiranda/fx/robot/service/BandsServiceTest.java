package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.BandsDto;
import br.allandemiranda.fx.robot.dto.create.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.BandsMapper;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.repository.BandsRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BandsServiceTest extends AbstractChartObjectServiceTest<Bands, BandsDto, BandsCreateDto> {

  @Mock
  @Getter
  private Bands model;

  @Mock
  @Getter
  private BandsRepository repository;

  @Spy
  @Getter
  private BandsMapper mapper;

  @InjectMocks
  @Getter
  private BandsService service;

}