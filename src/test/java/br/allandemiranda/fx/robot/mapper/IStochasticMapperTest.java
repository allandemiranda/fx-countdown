package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IStochasticDto;
import br.allandemiranda.fx.robot.dto.create.IStochasticCreateDto;
import br.allandemiranda.fx.robot.enums.PriceField;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IStochastic;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IStochasticMapperTest extends InputObjectMapperTest<IStochastic, IStochasticDto, IStochasticCreateDto> {

  private final short kPeriod = 1;
  private final short dPeriod = 2;
  private final short slowing = 3;

  @InjectMocks
  @Getter
  private IStochasticMapper mapper;

  @Mock
  @Getter
  private IStochastic model;

  @Mock
  @Getter
  private IStochasticCreateDto createDto;

  @Mock
  private SmoothingMethod method;

  @Mock
  private PriceField priceField;

  @Override
  protected void whenExtraParameters(IStochastic model) {
    Mockito.when(model.kPeriod()).thenReturn(this.kPeriod);
    Mockito.when(model.dPeriod()).thenReturn(this.dPeriod);
    Mockito.when(model.slowing()).thenReturn(this.slowing);
    Mockito.when(model.method()).thenReturn(this.method);
    Mockito.when(model.priceField()).thenReturn(this.priceField);
  }

  @Override
  protected void whenExtraParameters(IStochasticCreateDto createDto) {
    Mockito.when(createDto.kPeriod()).thenReturn(this.kPeriod);
    Mockito.when(createDto.dPeriod()).thenReturn(this.dPeriod);
    Mockito.when(createDto.slowing()).thenReturn(this.slowing);
    Mockito.when(createDto.method()).thenReturn(this.method);
    Mockito.when(createDto.priceField()).thenReturn(this.priceField);
  }

  @Override
  protected void thenExtraParameters(IStochastic model, IStochasticDto dto) {
    Assertions.assertEquals(model.kPeriod(), dto.kPeriod());
    Assertions.assertEquals(model.dPeriod(), dto.dPeriod());
    Assertions.assertEquals(model.slowing(), dto.slowing());
    Assertions.assertEquals(model.method(), dto.method());
    Assertions.assertEquals(model.priceField(), dto.priceField());
  }

  @Override
  protected void thenExtraParameters(IStochasticCreateDto createDto, IStochastic model) {
    Assertions.assertEquals(createDto.kPeriod(), model.kPeriod());
    Assertions.assertEquals(createDto.dPeriod(), model.dPeriod());
    Assertions.assertEquals(createDto.slowing(), model.slowing());
    Assertions.assertEquals(createDto.method(), model.method());
    Assertions.assertEquals(createDto.priceField(), model.priceField());
  }
}