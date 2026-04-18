package br.allandemiranda.fx.robot.mapper.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractChartObjectMapperTest<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  protected abstract M getModel();

  protected abstract C getCreateDto();

  protected abstract ChartObjectMapper<M, D, C> getMapper();

  protected abstract void whenExtraParameters(M model);

  protected abstract void whenExtraParameters(C createDto);

  protected abstract void thenExtraParameters(M model, D dto);

  protected abstract void thenExtraParameters(C createDto, M model);

  @Test
  void toDto_test() {
    //given
    UUID id = Mockito.mock(UUID.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = Mockito.mock(UUID.class);
    M model = this.getModel();

    //when
    Mockito.when(model.id()).thenReturn(id);
    Mockito.when(model.timestamp()).thenReturn(timestamp);
    Mockito.when(chartDto.id()).thenReturn(chartId);
    this.whenExtraParameters(model);
    D dto = this.getMapper().toDto(chartDto, model);

    //then
    Assertions.assertNotNull(dto);
    Assertions.assertEquals(model.id(), dto.id());
    Assertions.assertEquals(chartDto, dto.chartDto());
    Assertions.assertEquals(chartDto.id(), dto.chartDto().id());
    Assertions.assertEquals(model.timestamp(), dto.timestamp());
    this.thenExtraParameters(model, dto);
  }

  @Test
  void toModel_test() {
    //given
    UUID id = Mockito.mock(UUID.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = Mockito.mock(UUID.class);
    C createDto = this.getCreateDto();
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);

    //when
    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(createDto.timestamp()).thenReturn(timestamp);
    this.whenExtraParameters(createDto);
    M model = this.getMapper().toModel(id, chartDto, createDto);

    //then
    Assertions.assertNotNull(model);
    Assertions.assertEquals(id, model.id());
    Assertions.assertEquals(chartId, model.chartId());
    Assertions.assertEquals(timestamp, model.timestamp());
    this.thenExtraParameters(createDto, model);
  }
}