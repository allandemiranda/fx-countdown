package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.SymbolDto;
import br.allandemiranda.fx.robot.dto.create.TickCreateDto;
import br.allandemiranda.fx.robot.mapper.TickMapper;
import br.allandemiranda.fx.robot.model.Tick;
import br.allandemiranda.fx.robot.repository.TickRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class TickServiceTest {

  @InjectMocks
  private TickService service;

  @Mock
  private TickRepository repository;

  @Spy
  private TickMapper mapper;

  @Test
  void getBySymbolNameAndTimestamp_ShouldReturnDto_WhenModelExists() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    Tick tick = Mockito.mock(Tick.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolNameAndTimestamp(symbolName, timestamp)).thenReturn(Mono.just(tick));
    Mockito.when(tick.timestamp()).thenReturn(timestamp);

    //then
    StepVerifier.create(this.service.get(symbolDto, timestamp)).assertNext(dto -> {
      Assertions.assertNotNull(dto);

      Assertions.assertNotNull(dto.symbolDto());
      Assertions.assertEquals(symbolName, dto.symbolDto().name());

      Assertions.assertEquals(timestamp, dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getBySymbolNameAndTimestamp_ShouldReturnVoid_WhenModelNotExists() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findBySymbolNameAndTimestamp(symbolName, timestamp)).thenReturn(Mono.empty());

    //then
    StepVerifier.create(this.service.get(symbolDto, timestamp)).expectSubscription().expectNextCount(0).expectComplete().verify();
  }

  @Test
  void getAll_ShouldReturnFluxOfDtos() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    Tick tick = Mockito.mock(Tick.class);

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findAllBySymbolName(symbolName)).thenReturn(Flux.just(tick));
    Mockito.when(tick.timestamp()).thenReturn(timestamp);

    //then
    StepVerifier.create(this.service.get(symbolDto)).assertNext(dto -> {
      Assertions.assertNotNull(dto);

      Assertions.assertNotNull(dto.symbolDto());
      Assertions.assertEquals(symbolName, dto.symbolDto().name());

      Assertions.assertEquals(timestamp, dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getAll_ShouldReturnEmptyFlux_IfNotExistsChartDto() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findAllBySymbolName(symbolName)).thenReturn(Flux.empty());

    //then
    StepVerifier.create(this.service.get(symbolDto).collectList()).assertNext(dtos -> Assertions.assertEquals(0, dtos.size())).verifyComplete();
  }

  @Test
  void create_ShouldReturnModel() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    TickCreateDto tickCreateDto = Mockito.mock(TickCreateDto.class);
    Tick tick = Mockito.mock(Tick.class);

    //when
    Mockito.doReturn(tick).when(mapper).toModel(Mockito.any(UUID.class), Mockito.eq(symbolDto), Mockito.eq(tickCreateDto));
    Mockito.when(this.repository.save(tick)).thenReturn(Mono.just(tick));

    //then
    StepVerifier.create(this.service.create(symbolDto, tickCreateDto)).assertNext(Assertions::assertNotNull).verifyComplete();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    SymbolDto symbolDto = Mockito.mock(SymbolDto.class);
    String symbolName = "EURUSD";

    //when
    Mockito.when(symbolDto.name()).thenReturn(symbolName);
    Mockito.when(this.repository.deleteAllBySymbolName(symbolDto.name())).thenReturn(Mono.empty());

    //then
    StepVerifier.create(this.service.delete(symbolDto)).expectNextCount(0).verifyComplete();
  }
}