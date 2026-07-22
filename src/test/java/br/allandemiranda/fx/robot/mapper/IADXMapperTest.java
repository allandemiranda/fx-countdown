package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IADX;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IADXMapperTest extends InputObjectMapperTest<IADX, IADXDto, IADXCreateDto> {

  private final short period = 1;

  @InjectMocks
  @Getter
  private IADXMapper mapper;

  @Mock
  @Getter
  private IADX model;

  @Mock
  @Getter
  private IADXCreateDto createDto;

  @Override
  protected void whenExtraParameters(IADX model) {
    Mockito.when(model.period()).thenReturn(this.period);
  }

  @Override
  protected void whenExtraParameters(IADXCreateDto createDto) {
    Mockito.when(createDto.period()).thenReturn(this.period);
  }

  @Override
  protected void thenExtraParameters(IADX model, IADXDto dto) {
    Assertions.assertEquals(model.period(), dto.period());
  }

  @Override
  protected void thenExtraParameters(IADXCreateDto createDto, IADX model) {
    Assertions.assertEquals(createDto.period(), model.period());
  }
}