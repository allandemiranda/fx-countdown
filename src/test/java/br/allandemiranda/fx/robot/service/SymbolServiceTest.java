package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.SymbolCreateDto;
import br.allandemiranda.fx.robot.mapper.SymbolMapper;
import br.allandemiranda.fx.robot.model.Symbol;
import br.allandemiranda.fx.robot.repository.SymbolRepository;
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
class SymbolServiceTest {

  @InjectMocks
  private SymbolService service;

  @Mock
  private SymbolRepository repository;

  @Spy
  private SymbolMapper mapper;

  @Test
  void getById_ShouldReturnDto_WhenModelExists() {
    //given
    String symbolName = "EURUSD";
    Symbol symbol = Mockito.mock(Symbol.class);
    //when
    Mockito.when(this.repository.findById(symbolName)).thenReturn(Mono.just(symbol));
    Mockito.when(symbol.name()).thenReturn(symbolName);
    //then
    StepVerifier.create(this.service.get(symbolName)).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertEquals(symbolName, dto.name());
    }).verifyComplete();
  }

  @Test
  void getById_ShouldReturnVoid_WhenModelNotExists() {
    //given
    String symbolName = "EURUSD";
    //when
    Mockito.when(this.repository.findById(symbolName)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.service.get(symbolName)).expectSubscription().expectNextCount(0).expectComplete().verify();
  }

  @Test
  void getAll_ShouldReturnFluxOfDtos() {
    //given
    String symbolName = "EURUSD";
    Symbol symbol = Mockito.mock(Symbol.class);
    //when
    Mockito.when(symbol.name()).thenReturn(symbolName);
    Mockito.when(this.repository.findAll()).thenReturn(Flux.just(symbol));
    //then
    StepVerifier.create(this.service.get()).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertEquals(symbolName, dto.name());
    }).verifyComplete();
  }

  @Test
  void getAll_ShouldReturnEmptyFlux_IfNotExistsChartDto() {
    //given
    //when
    Mockito.when(this.repository.findAll()).thenReturn(Flux.empty());
    //then
    StepVerifier.create(this.service.get().collectList()).assertNext(dtos -> {
      Assertions.assertEquals(0, dtos.size());
    }).verifyComplete();
  }

  @Test
  void create_ShouldReturnModel() {
    //given
    SymbolCreateDto symbolCreateDto = Mockito.mock(SymbolCreateDto.class);
    Symbol symbol = Mockito.mock(Symbol.class);
    //when
    Mockito.doReturn(symbol).when(mapper).toModel(symbolCreateDto);
    Mockito.when(repository.save(symbol)).thenReturn(Mono.just(symbol));
    //then
    StepVerifier.create(this.service.create(symbolCreateDto)).assertNext(Assertions::assertNotNull).verifyComplete();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    String symbolName = "EURUSD";
    //when
    Mockito.when(this.repository.deleteById(symbolName)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.service.delete(symbolName)).expectNextCount(0).verifyComplete();
  }
}