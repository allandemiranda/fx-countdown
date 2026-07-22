package br.allandemiranda.fx.robot.mapper.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.CreateInputObjectDto;
import br.allandemiranda.fx.robot.dto.definition.InputObjectDto;
import br.allandemiranda.fx.robot.model.definition.InputObjectModel;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class InputObjectMapperTest<M extends InputObjectModel, D extends InputObjectDto, C extends CreateInputObjectDto> {

  protected abstract InputObjectMapper<M, D, C> getMapper();

  protected abstract M getModel();

  protected abstract C getCreateDto();

  protected abstract void whenExtraParameters(M model);

  protected abstract void whenExtraParameters(C createDto);

  protected abstract void thenExtraParameters(M model, D dto);

  protected abstract void thenExtraParameters(C createDto, M model);

  @Test
  void toDto() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    M model = this.getModel();

    //when
    this.whenExtraParameters(model);
    D dto = this.getMapper().toDto(chartDto, model);

    //then
    Assertions.assertNotNull(dto);
    Assertions.assertEquals(chartDto, dto.chartDto());
    this.thenExtraParameters(model, dto);
  }

  @Test
  void toModel() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = UUID.randomUUID();
    C createDto = this.getCreateDto();

    //when
    Mockito.when(chartDto.id()).thenReturn(chartId);
    this.whenExtraParameters(createDto);
    M model = this.getMapper().toModel(chartDto, createDto);

    //then
    Assertions.assertNotNull(model);
    Assertions.assertEquals(chartId, model.chartId());
    this.thenExtraParameters(createDto, model);
  }
}