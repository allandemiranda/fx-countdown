package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.repository.contract.InputObjectRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public abstract class AbstractInputObjectServiceTest<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  @Mock
  private ChartDto chartDto;

  protected abstract InputObjectRepository<M> getRepository();

  protected abstract InputObjectService<M, D, C> getService();

  protected abstract M getModel();

  @Test
  void getByChartDto_ShouldReturnDto_WhenModelExists() {
    //given
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().findByChartId(this.chartDto.id())).thenReturn(Mono.just(this.getModel()));
    //then
    StepVerifier.create(this.getService().get(this.chartDto)).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertEquals(uuid, dto.chartDto().id());
    }).verifyComplete();
  }

  @Test
  void getByChartDto_ShouldReturnVoid_WhenModelNotExists() {
    //given
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().findByChartId(uuid)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().get(this.chartDto)).expectSubscription().expectComplete().verify();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().deleteByChartId(this.chartDto.id())).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().delete(this.chartDto)).verifyComplete();
  }
}