package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.CandlestickDto;
import br.allandemiranda.fx.robot.dto.create.CandlestickCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.Candlestick;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CandlestickMapperTest extends AbstractChartObjectMapperTest<Candlestick, CandlestickDto, CandlestickCreateDto> {

  @InjectMocks
  @Getter
  private CandlestickMapper mapper;

  @Mock
  @Getter
  private Candlestick model;

  @Mock
  @Getter
  private CandlestickCreateDto createDto;

  @Mock
  private BigDecimal open;

  @Mock
  private BigDecimal high;

  @Mock
  private BigDecimal low;

  @Mock
  private BigDecimal close;

  @Override
  protected void whenExtraParameters(Candlestick model) {
    Mockito.when(model.open()).thenReturn(this.open);
    Mockito.when(model.high()).thenReturn(this.high);
    Mockito.when(model.low()).thenReturn(this.low);
    Mockito.when(model.close()).thenReturn(this.close);
  }

  @Override
  protected void whenExtraParameters(CandlestickCreateDto createDto) {
    Mockito.when(createDto.open()).thenReturn(this.open);
    Mockito.when(createDto.high()).thenReturn(this.high);
    Mockito.when(createDto.low()).thenReturn(this.low);
    Mockito.when(createDto.close()).thenReturn(this.close);
  }

  @Override
  protected void thenExtraParameters(Candlestick model, CandlestickDto dto) {
    Assertions.assertEquals(model.open(), dto.open());
    Assertions.assertEquals(model.high(), dto.high());
    Assertions.assertEquals(model.low(), dto.low());
    Assertions.assertEquals(model.close(), dto.close());
  }

  @Override
  protected void thenExtraParameters(CandlestickCreateDto createDto, Candlestick model) {
    Assertions.assertEquals(createDto.open(), model.open());
    Assertions.assertEquals(createDto.high(), model.high());
    Assertions.assertEquals(createDto.low(), model.low());
    Assertions.assertEquals(createDto.close(), model.close());
  }

}