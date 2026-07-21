package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.create.IRSICreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.mapper.contract.AbstractInputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IRSI;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IRSIMapperTest extends AbstractInputObjectMapperTest<IRSI, IRSIDto, IRSICreateDto> {

  private final short period = 1;

  @InjectMocks
  @Getter
  private IRSIMapper mapper;

  @Mock
  @Getter
  private IRSI model;

  @Mock
  @Getter
  private IRSICreateDto createDto;

  @Mock
  private AppliedPrice applyTo;

  @Override
  protected void whenExtraParameters(IRSI model) {
    Mockito.when(model.period()).thenReturn(this.period);
    Mockito.when(model.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void whenExtraParameters(IRSICreateDto createDto) {
    Mockito.when(createDto.period()).thenReturn(this.period);
    Mockito.when(createDto.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void thenExtraParameters(IRSI model, IRSIDto dto) {
    Assertions.assertEquals(model.period(), dto.period());
    Assertions.assertEquals(model.applyTo(), dto.applyTo());
  }

  @Override
  protected void thenExtraParameters(IRSICreateDto createDto, IRSI model) {
    Assertions.assertEquals(createDto.period(), model.period());
    Assertions.assertEquals(createDto.applyTo(), model.applyTo());
  }
}