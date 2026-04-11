package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.GarchTrading;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchTradingMapperTest extends AbstractChartObjectMapperTest<GarchTrading, GarchTradingDto, GarchTradingCreateDto> {

  @InjectMocks
  @Getter
  private GarchTradingMapper mapper;

  @Mock
  @Getter
  private GarchTrading model;

  @Mock
  @Getter
  private GarchTradingCreateDto createDto;

  @Mock
  private BigDecimal buyOpenPrice;

  @Mock
  private BigDecimal buyTpPrice;

  @Mock
  private BigDecimal buySlPrice;

  @Mock
  private BigDecimal sellOpenPrice;

  @Mock
  private BigDecimal sellTpPrice;

  @Mock
  private BigDecimal sellSlPrice;

  @Override
  protected void whenExtraParameters(GarchTrading model) {
    Mockito.when(model.buyOpenPrice()).thenReturn(buyOpenPrice);
    Mockito.when(model.buyTpPrice()).thenReturn(buyTpPrice);
    Mockito.when(model.buySlPrice()).thenReturn(buySlPrice);
    Mockito.when(model.buyOpenPrice()).thenReturn(sellOpenPrice);
    Mockito.when(model.sellTpPrice()).thenReturn(sellTpPrice);
    Mockito.when(model.sellSlPrice()).thenReturn(sellSlPrice);
  }

  @Override
  protected void whenExtraParameters(GarchTradingCreateDto createDto) {
    Mockito.when(createDto.buyOpenPrice()).thenReturn(buyOpenPrice);
    Mockito.when(createDto.buyTpPrice()).thenReturn(buyTpPrice);
    Mockito.when(createDto.buySlPrice()).thenReturn(buySlPrice);
    Mockito.when(createDto.sellOpenPrice()).thenReturn(sellOpenPrice);
    Mockito.when(createDto.sellTpPrice()).thenReturn(sellTpPrice);
    Mockito.when(createDto.sellSlPrice()).thenReturn(sellSlPrice);
  }

  @Override
  protected void thenExtraParameters(GarchTrading model, GarchTradingDto dto) {
    Assertions.assertEquals(model.buyOpenPrice(), dto.buyOpenPrice());
    Assertions.assertEquals(model.buyTpPrice(), dto.buyTpPrice());
    Assertions.assertEquals(model.buySlPrice(), dto.buySlPrice());
    Assertions.assertEquals(model.sellOpenPrice(), dto.sellOpenPrice());
    Assertions.assertEquals(model.sellTpPrice(), dto.sellTpPrice());
    Assertions.assertEquals(model.sellSlPrice(), dto.sellSlPrice());
  }

  @Override
  protected void thenExtraParameters(GarchTradingCreateDto createDto, GarchTrading model) {
    Assertions.assertEquals(createDto.buyOpenPrice(), model.buyOpenPrice());
    Assertions.assertEquals(createDto.buyTpPrice(), model.buyTpPrice());
    Assertions.assertEquals(createDto.buySlPrice(), model.buySlPrice());
    Assertions.assertNull(model.closeBuyTime());
    Assertions.assertEquals(0, model.buyPoints());
    Assertions.assertNull(model.buyDealReason());
    Assertions.assertEquals(createDto.sellOpenPrice(), model.sellOpenPrice());
    Assertions.assertEquals(createDto.sellTpPrice(), model.sellTpPrice());
    Assertions.assertEquals(createDto.sellSlPrice(), model.sellSlPrice());
    Assertions.assertNull(model.closeSellTime());
    Assertions.assertEquals(0, model.sellPoints());
    Assertions.assertNull(model.sellDealReason());
  }

}