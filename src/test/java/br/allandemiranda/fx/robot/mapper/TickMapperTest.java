package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.base.TickDto;
import br.allandemiranda.fx.robot.dto.create.TickCreateDto;
import br.allandemiranda.fx.robot.model.Tick;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TickMapperTest {

  @InjectMocks
  private TickMapper tickMapper;

  @Test
  void toDto_test() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    Tick tick = Mockito.mock(Tick.class);
    UUID id = Mockito.mock(UUID.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    BigDecimal ask = Mockito.mock(BigDecimal.class);
    BigDecimal bid = Mockito.mock(BigDecimal.class);

    //when
    Mockito.when(tick.id()).thenReturn(id);
    Mockito.when(tick.timestamp()).thenReturn(timestamp);
    Mockito.when(tick.ask()).thenReturn(ask);
    Mockito.when(tick.bid()).thenReturn(bid);
    TickDto tickDto = this.tickMapper.toDto(symbolDto, tick);

    //then
    Assertions.assertNotNull(tickDto);
    Assertions.assertEquals(id, tickDto.id());
    Assertions.assertEquals(timestamp, tickDto.timestamp());
    Assertions.assertEquals(ask, tickDto.ask());
    Assertions.assertEquals(bid, tickDto.bid());
  }

  @Test
  void toModel_test() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    TickCreateDto tickCreateDto = Mockito.mock(TickCreateDto.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    BigDecimal ask = Mockito.mock(BigDecimal.class);
    BigDecimal bid = Mockito.mock(BigDecimal.class);
    UUID id = Mockito.mock(UUID.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(tickCreateDto.timestamp()).thenReturn(timestamp);
    Mockito.when(tickCreateDto.ask()).thenReturn(ask);
    Mockito.when(tickCreateDto.bid()).thenReturn(bid);
    Tick tick = this.tickMapper.toModel(id, symbolDto, tickCreateDto);

    //then
    Assertions.assertNotNull(tick);
    Assertions.assertEquals(id, tick.id());
    Assertions.assertEquals(symbolName, tick.symbolName());
    Assertions.assertEquals(timestamp, tick.timestamp());
    Assertions.assertEquals(ask, tick.ask());
    Assertions.assertEquals(bid, tick.bid());
  }
}