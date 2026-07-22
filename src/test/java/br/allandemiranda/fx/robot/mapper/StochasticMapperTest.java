package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.StochasticDto;
import br.allandemiranda.fx.robot.dto.create.StochasticCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.Stochastic;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StochasticMapperTest extends ChartObjectMapperTest<Stochastic, StochasticDto, StochasticCreateDto> {

  @InjectMocks
  @Getter
  private StochasticMapper mapper;

  @Mock
  @Getter
  private Stochastic model;

  @Mock
  @Getter
  private StochasticCreateDto createDto;

  @Mock
  private BigDecimal mainLine;

  @Mock
  private BigDecimal signalLine;

  @Override
  protected void whenExtraParameters(Stochastic model) {
    Mockito.when(model.mainLine()).thenReturn(this.mainLine);
    Mockito.when(model.signalLine()).thenReturn(this.signalLine);
  }

  @Override
  protected void whenExtraParameters(StochasticCreateDto createDto) {
    Mockito.when(createDto.mainLine()).thenReturn(this.mainLine);
    Mockito.when(createDto.signalLine()).thenReturn(this.signalLine);
  }

  @Override
  protected void thenExtraParameters(Stochastic model, StochasticDto dto) {
    Assertions.assertEquals(model.mainLine(), dto.mainLine());
    Assertions.assertEquals(model.signalLine(), dto.signalLine());
  }

  @Override
  protected void thenExtraParameters(StochasticCreateDto createDto, Stochastic model) {
    Assertions.assertEquals(createDto.mainLine(), model.mainLine());
    Assertions.assertEquals(createDto.signalLine(), model.signalLine());
  }

}