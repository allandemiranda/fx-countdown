package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IMACDDto;
import br.allandemiranda.fx.robot.dto.create.IMACDCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.mapper.contract.AbstractInputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IMACD;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IMACDMapperTest extends AbstractInputObjectMapperTest<IMACD, IMACDDto, IMACDCreateDto> {

  private final short fastEma = 1;
  private final short slowEma = 2;
  private final short macdSma = 3;

  @InjectMocks
  @Getter
  private IMACDMapper mapper;

  @Mock
  @Getter
  private IMACD model;

  @Mock
  @Getter
  private IMACDCreateDto createDto;

  @Mock
  private AppliedPrice applyTo;

  @Override
  protected void whenExtraParameters(IMACD model) {
    Mockito.when(model.fastEma()).thenReturn(fastEma);
    Mockito.when(model.slowEma()).thenReturn(slowEma);
    Mockito.when(model.macdSma()).thenReturn(macdSma);
    Mockito.when(model.applyTo()).thenReturn(applyTo);
  }

  @Override
  protected void whenExtraParameters(IMACDCreateDto createDto) {
    Mockito.when(createDto.fastEma()).thenReturn(fastEma);
    Mockito.when(createDto.slowEma()).thenReturn(slowEma);
    Mockito.when(createDto.macdSma()).thenReturn(macdSma);
    Mockito.when(createDto.applyTo()).thenReturn(applyTo);
  }

  @Override
  protected void thenExtraParameters(IMACD model, IMACDDto dto) {
    Assertions.assertEquals(model.fastEma(), dto.fastEma());
    Assertions.assertEquals(model.slowEma(), dto.slowEma());
    Assertions.assertEquals(model.macdSma(), dto.macdSma());
    Assertions.assertEquals(model.applyTo(), dto.applyTo());
  }

  @Override
  protected void thenExtraParameters(IMACDCreateDto createDto, IMACD model) {
    Assertions.assertEquals(createDto.fastEma(), model.fastEma());
    Assertions.assertEquals(createDto.slowEma(), model.slowEma());
    Assertions.assertEquals(createDto.macdSma(), model.macdSma());
    Assertions.assertEquals(createDto.applyTo(), model.applyTo());
  }
}