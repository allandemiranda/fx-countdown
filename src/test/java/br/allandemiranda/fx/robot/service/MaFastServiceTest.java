package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.MaFastMapper;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.repository.MaFastRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaFastServiceTest extends AbstractChartObjectServiceTest<MaFast, MaFastDto, MaFastCreateDto> {

  @Mock
  @Getter
  private MaFast model;

  @Mock
  @Getter
  private MaFastRepository repository;

  @Spy
  @Getter
  private MaFastMapper mapper;

  @InjectMocks
  @Getter
  private MaFastService service;

}