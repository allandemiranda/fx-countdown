package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.model.ADX;
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
class ADXMapperTest {

  @InjectMocks
  private ADXMapper mapper;

  @Test
  void toDto() {
    //given
    UUID id = Mockito.mock(UUID.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = Mockito.mock(UUID.class);
    ADX model = Mockito.mock(ADX.class);

    BigDecimal mainLine = Mockito.mock(BigDecimal.class);
    BigDecimal plusDiLine = Mockito.mock(BigDecimal.class);
    BigDecimal minusDiLine = Mockito.mock(BigDecimal.class);
    //when
    Mockito.when(model.id()).thenReturn(id);
    Mockito.when(model.timestamp()).thenReturn(timestamp);
    Mockito.when(chartDto.id()).thenReturn(chartId);

    Mockito.when(model.mainLine()).thenReturn(mainLine);
    Mockito.when(model.plusDiLine()).thenReturn(plusDiLine);
    Mockito.when(model.minusDiLine()).thenReturn(minusDiLine);
    //then
    ADXDto dto = this.mapper.toDto(chartDto, model);
    Assertions.assertEquals(dto.id(), model.id());
    Assertions.assertEquals(dto.chartDto(), chartDto);
    Assertions.assertEquals(dto.chartDto().id(), chartDto.id());
    Assertions.assertEquals(dto.timestamp(), model.timestamp());

    Assertions.assertEquals(dto.mainLine(), model.mainLine());
    Assertions.assertEquals(dto.plusDiLine(), model.plusDiLine());
    Assertions.assertEquals(dto.minusDiLine(), model.minusDiLine());
  }

  @Test
  void toModel() {
    //given
    UUID id = Mockito.mock(UUID.class);
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    UUID chartId = Mockito.mock(UUID.class);
    ADXCreateDto createDto = Mockito.mock(ADXCreateDto.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);

    BigDecimal mainLine = Mockito.mock(BigDecimal.class);
    BigDecimal plusDiLine = Mockito.mock(BigDecimal.class);
    BigDecimal minusDiLine = Mockito.mock(BigDecimal.class);
    //when
    Mockito.when(chartDto.id()).thenReturn(chartId);
    Mockito.when(createDto.timestamp()).thenReturn(timestamp);

    Mockito.when(createDto.mainLine()).thenReturn(mainLine);
    Mockito.when(createDto.plusDiLine()).thenReturn(plusDiLine);
    Mockito.when(createDto.minusDiLine()).thenReturn(minusDiLine);
    //then
    ADX model = this.mapper.toModel(id, chartDto, createDto);
    Assertions.assertEquals(model.id(), id);
    Assertions.assertEquals(chartId, model.chartId());
    Assertions.assertEquals(timestamp, model.timestamp());

    Assertions.assertEquals(mainLine, model.mainLine());
    Assertions.assertEquals(plusDiLine, model.plusDiLine());
    Assertions.assertEquals(minusDiLine, model.minusDiLine());
  }
}