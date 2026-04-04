package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.repository.ADXRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ADXServiceTest extends AbstractChartObjectServiceTest<ADX, ADXDto, ADXCreateDto> {

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

  @InjectMocks
  @Getter
  private ADXService service;

}