package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.MACD;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MACDMapperTest extends ChartObjectMapperTest<MACD, MACDDto, MACDCreateDto> {

  @InjectMocks
  @Getter
  private MACDMapper mapper;

  @Mock
  @Getter
  private MACD model;

  @Mock
  @Getter
  private MACDCreateDto createDto;

  @Mock
  private BigDecimal mainLine;

  @Mock
  private BigDecimal signalLine;


  @Override
  protected void whenExtraParameters(MACD model) {
    Mockito.when(model.mainLine()).thenReturn(this.mainLine);
    Mockito.when(model.signalLine()).thenReturn(this.signalLine);
  }

  @Override
  protected void whenExtraParameters(MACDCreateDto createDto) {
    Mockito.when(createDto.mainLine()).thenReturn(this.mainLine);
    Mockito.when(createDto.signalLine()).thenReturn(this.signalLine);
  }

  @Override
  protected void thenExtraParameters(MACD model, MACDDto dto) {
    Assertions.assertEquals(model.mainLine(), dto.mainLine());
    Assertions.assertEquals(model.signalLine(), dto.signalLine());
  }

  @Override
  protected void thenExtraParameters(MACDCreateDto createDto, MACD model) {
    Assertions.assertEquals(createDto.mainLine(), model.mainLine());
    Assertions.assertEquals(createDto.signalLine(), model.signalLine());
  }

}