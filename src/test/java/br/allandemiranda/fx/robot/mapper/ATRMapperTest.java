package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.ATR;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ATRMapperTest extends AbstractChartObjectMapperTest<ATR, ATRDto, ATRCreateDto> {

  @InjectMocks
  @Getter
  private ATRMapper mapper;

  @Mock
  @Getter
  private ATR model;

  @Mock
  @Getter
  private ATRCreateDto createDto;

  @Mock
  private BigDecimal atr;

  @Override
  protected void whenExtraParameters(ATR model) {
    Mockito.when(model.atr()).thenReturn(this.atr);
  }

  @Override
  protected void whenExtraParameters(ATRCreateDto createDto) {
    Mockito.when(createDto.atr()).thenReturn(this.atr);
  }

  @Override
  protected void thenExtraParameters(ATR model, ATRDto dto) {
    Assertions.assertEquals(model.atr(), dto.atr());
  }

  @Override
  protected void thenExtraParameters(ATRCreateDto createDto, ATR model) {
    Assertions.assertEquals(createDto.atr(), model.atr());
  }

}