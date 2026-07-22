package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.ADX;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ADXMapperTest extends ChartObjectMapperTest<ADX, ADXDto, ADXCreateDto> {

  @InjectMocks
  @Getter
  private ADXMapper mapper;

  @Mock
  @Getter
  private ADX model;

  @Mock
  @Getter
  private ADXCreateDto createDto;

  @Mock
  private BigDecimal mainLine;

  @Mock
  private BigDecimal plusDiLine;

  @Mock
  private BigDecimal minusDiLine;

  @Override
  protected void whenExtraParameters(ADX model) {
    Mockito.when(model.mainLine()).thenReturn(this.mainLine);
    Mockito.when(model.plusDiLine()).thenReturn(this.plusDiLine);
    Mockito.when(model.minusDiLine()).thenReturn(this.minusDiLine);
  }

  @Override
  protected void whenExtraParameters(ADXCreateDto createDto) {
    Mockito.when(createDto.mainLine()).thenReturn(this.mainLine);
    Mockito.when(createDto.plusDiLine()).thenReturn(this.plusDiLine);
    Mockito.when(createDto.minusDiLine()).thenReturn(this.minusDiLine);
  }

  @Override
  protected void thenExtraParameters(ADX model, ADXDto dto) {
    Assertions.assertEquals(model.mainLine(), dto.mainLine());
    Assertions.assertEquals(model.plusDiLine(), dto.plusDiLine());
    Assertions.assertEquals(model.minusDiLine(), dto.minusDiLine());
  }

  @Override
  protected void thenExtraParameters(ADXCreateDto createDto, ADX model) {
    Assertions.assertEquals(createDto.mainLine(), model.mainLine());
    Assertions.assertEquals(createDto.plusDiLine(), model.plusDiLine());
    Assertions.assertEquals(createDto.minusDiLine(), model.minusDiLine());
  }

}