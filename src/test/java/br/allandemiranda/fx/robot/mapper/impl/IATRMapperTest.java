package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.IATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.impl.IATR;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IATRMapperTest extends InputObjectMapperTest<IATR, IATRDto, IATRCreateDto> {

  private final short period = 1;

  @InjectMocks
  @Getter
  private IATRMapper mapper;

  @Mock
  @Getter
  private IATR model;

  @Mock
  @Getter
  private IATRCreateDto createDto;

  @Override
  protected void whenExtraParameters(IATR model) {
    Mockito.when(model.period()).thenReturn(this.period);
  }

  @Override
  protected void whenExtraParameters(IATRCreateDto createDto) {
    Mockito.when(createDto.period()).thenReturn(this.period);
  }

  @Override
  protected void thenExtraParameters(IATR model, IATRDto dto) {
    Assertions.assertEquals(model.period(), dto.period());
  }

  @Override
  protected void thenExtraParameters(IATRCreateDto createDto, IATR model) {
    Assertions.assertEquals(createDto.period(), model.period());
  }
}