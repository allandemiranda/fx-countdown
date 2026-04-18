package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import br.allandemiranda.fx.robot.repository.contract.InputObjectRepository;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractInputObjectServiceTest<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  @Mock
  private ChartDto chartDto;

  protected abstract C getCreateDto();

  protected abstract InputObjectRepository<M> getRepository();

  protected abstract InputObjectService<M, D, C> getService();

  protected abstract InputObjectMapper<M, D, C> getMapper();

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
  void create_ShouldReturnModel() {
    //given
    UUID uuid = Mockito.mock(UUID.class);

    //when
    Mockito.when(this.chartDto.id()).thenReturn(uuid);
    Mockito.doReturn(this.getModel()).when(this.getMapper()).toModel(Mockito.eq(this.chartDto), Mockito.eq(this.getCreateDto()));
    Mockito.when(this.getRepository().save(this.getModel())).thenReturn(Mono.just(this.getModel()));

    //then
    StepVerifier.create(this.getService().create(this.chartDto, this.getCreateDto())).assertNext(dto -> {
      Assertions.assertNotNull(dto);
      Assertions.assertNotNull(dto.chartDto());
      Assertions.assertEquals(uuid, dto.chartDto().id());
    }).verifyComplete();
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