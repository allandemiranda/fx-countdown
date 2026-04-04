package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public abstract class AbstractChartObjectServiceTest<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  @Mock
  private ChartDto chartDto;

  protected abstract ChartObjectRepository<M> getRepository();

  protected abstract ChartObjectService<M, D, C> getService();

  protected abstract M getModel();

  @Test
  void getByChartDtoAndTimestamp_ShouldReturnDto_WhenModelExists() {
    //given
    UUID chartDtoUuid = Mockito.mock(UUID.class);
    UUID modelUuid = Mockito.mock(UUID.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(chartDtoUuid);
    Mockito.when(this.getModel().id()).thenReturn(modelUuid);
    Mockito.when(this.getModel().timestamp()).thenReturn(timestamp);
    Mockito.when(this.getRepository().findByChartIdAndTimestamp(this.chartDto.id(), this.getModel().timestamp())).thenReturn(Mono.just(this.getModel()));
    //then
    StepVerifier.create(this.getService().get(this.chartDto, this.getModel().timestamp())).assertNext(dto -> {
      Assertions.assertNotNull(dto.id());
      Assertions.assertEquals(this.getModel().id(), dto.id());

      Assertions.assertNotNull(dto.chartDto());
      Assertions.assertEquals(chartDtoUuid, dto.chartDto().id());

      Assertions.assertNotNull(dto.timestamp());
      Assertions.assertEquals(this.getModel().timestamp(), dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getByChartDtoAndTimestamp_ShouldReturnVoid_WhenModelNotExists() {
    //given
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().findByChartIdAndTimestamp(uuid, timestamp)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().get(this.chartDto, timestamp)).expectSubscription().expectComplete().verify();
  }

  @Test
  void getAll_ShouldReturnFluxOfDtos() {
    //given
    UUID chartDtoUuid = Mockito.mock(UUID.class);
    UUID modelUuid = Mockito.mock(UUID.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(chartDtoUuid);
    Mockito.when(this.getModel().id()).thenReturn(modelUuid);
    Mockito.when(this.getModel().timestamp()).thenReturn(timestamp);
    Mockito.when(this.getRepository().findAllByChartId(this.chartDto.id())).thenReturn(Flux.just(this.getModel()));
    //then
    StepVerifier.create(this.getService().get(this.chartDto).collectList()).assertNext(dtos -> {
      Assertions.assertEquals(1, dtos.size());
      D dto = dtos.getFirst();

      Assertions.assertNotNull(dto.id());
      Assertions.assertEquals(this.getModel().id(), dto.id());

      Assertions.assertNotNull(dto.chartDto());
      Assertions.assertEquals(chartDtoUuid, dto.chartDto().id());

      Assertions.assertNotNull(dto.timestamp());
      Assertions.assertEquals(this.getModel().timestamp(), dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getAll_ShouldReturnEmptyFlux_IfNotExistsChartDto() {
    //given
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().findAllByChartId(this.chartDto.id())).thenReturn(Flux.just());
    //then
    StepVerifier.create(this.getService().get(this.chartDto).collectList()).assertNext(dtos -> {
      Assertions.assertEquals(0, dtos.size());
    }).verifyComplete();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().deleteAllByChartId(this.chartDto.id())).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().delete(this.chartDto)).verifyComplete();
  }
}