package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.ATRMapper;
import br.allandemiranda.fx.robot.model.ATR;
import br.allandemiranda.fx.robot.repository.ATRRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ATRServiceTest extends AbstractChartObjectServiceTest<ATR, ATRDto, ATRCreateDto> {

  @Mock
  @Getter
  private ATR model;

  @Mock
  @Getter
  private ATRRepository repository;

  @Spy
  @Getter
  private ATRMapper mapper;

  @InjectMocks
  @Getter
  private ATRService service;

}