package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.model.Symbol;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SymbolMapperTest {

  @InjectMocks
  private SymbolMapper symbolMapper;

  @Test
  void toDto_test() {
    //given
    Symbol symbol = Mockito.mock(Symbol.class);
    String symbolName = "EURUSD";
    BigDecimal point = Mockito.mock(BigDecimal.class);
    BigDecimal swapLong = Mockito.mock(BigDecimal.class);
    BigDecimal swapShort = Mockito.mock(BigDecimal.class);
    //when
    Mockito.when(symbol.name()).thenReturn(symbolName);
    Mockito.when(symbol.point()).thenReturn(point);
    Mockito.when(symbol.swapLong()).thenReturn(swapLong);
    Mockito.when(symbol.swapShort()).thenReturn(swapShort);
    SymbolDto symbolDto = symbolMapper.toDto(symbol);
    //then
    Assertions.assertNotNull(symbolDto);
    Assertions.assertEquals(symbolName, symbolDto.name());
    Assertions.assertEquals(point, symbolDto.point());
    Assertions.assertEquals(swapLong, symbolDto.swapLong());
    Assertions.assertEquals(swapShort, symbolDto.swapShort());
  }

  @Test
  void toModel_test() {
    //given
    SymbolCreateDto symbolCreateDto = Mockito.mock(SymbolCreateDto.class);
    String symbolName = "EURUSD";
    BigDecimal point = Mockito.mock(BigDecimal.class);
    BigDecimal swapLong = Mockito.mock(BigDecimal.class);
    BigDecimal swapShort = Mockito.mock(BigDecimal.class);
    //when
    Mockito.when(symbolCreateDto.name()).thenReturn(symbolName);
    Mockito.when(symbolCreateDto.point()).thenReturn(point);
    Mockito.when(symbolCreateDto.swapLong()).thenReturn(swapLong);
    Mockito.when(symbolCreateDto.swapShort()).thenReturn(swapShort);
    Symbol symbol = symbolMapper.toModel(symbolCreateDto);
    //then
    Assertions.assertNotNull(symbol);
    Assertions.assertEquals(symbolName, symbol.name());
    Assertions.assertEquals(point, symbol.point());
    Assertions.assertEquals(swapLong, symbol.swapLong());
    Assertions.assertEquals(swapShort, symbol.swapShort());
  }
}