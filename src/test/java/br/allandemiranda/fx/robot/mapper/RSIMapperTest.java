package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.RSI;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RSIMapperTest extends AbstractChartObjectMapperTest<RSI, RSIDto, RSICreateDto> {

  @InjectMocks
  @Getter
  private RSIMapper mapper;

  @Mock
  @Getter
  private RSI model;

  @Mock
  @Getter
  private RSICreateDto createDto;

  @Mock
  private BigDecimal rsi;

  @Override
  protected void whenExtraParameters(RSI model) {
    Mockito.when(model.rsi()).thenReturn(this.rsi);
  }

  @Override
  protected void whenExtraParameters(RSICreateDto createDto) {
    Mockito.when(createDto.rsi()).thenReturn(this.rsi);
  }

  @Override
  protected void thenExtraParameters(RSI model, RSIDto dto) {
    Assertions.assertEquals(model.rsi(), dto.rsi());
  }

  @Override
  protected void thenExtraParameters(RSICreateDto createDto, RSI model) {
    Assertions.assertEquals(createDto.rsi(), model.rsi());
  }

}