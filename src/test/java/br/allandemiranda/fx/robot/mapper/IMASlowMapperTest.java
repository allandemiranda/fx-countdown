package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.enums.SmoothingMethod;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IMASlow;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IMASlowMapperTest extends InputObjectMapperTest<IMASlow, IMASlowDto, IMASlowCreateDto> {

  private final short period = 1;
  private final short shift = 2;

  @InjectMocks
  @Getter
  private IMASlowMapper mapper;

  @Mock
  @Getter
  private IMASlow model;

  @Mock
  @Getter
  private IMASlowCreateDto createDto;

  @Mock
  private SmoothingMethod method;

  @Mock
  private AppliedPrice applyTo;

  @Override
  protected void whenExtraParameters(IMASlow model) {
    Mockito.when(model.period()).thenReturn(this.period);
    Mockito.when(model.shift()).thenReturn(this.shift);
    Mockito.when(model.method()).thenReturn(this.method);
    Mockito.when(model.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void whenExtraParameters(IMASlowCreateDto createDto) {
    Mockito.when(createDto.period()).thenReturn(this.period);
    Mockito.when(createDto.shift()).thenReturn(this.shift);
    Mockito.when(createDto.method()).thenReturn(this.method);
    Mockito.when(createDto.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void thenExtraParameters(IMASlow model, IMASlowDto dto) {
    Assertions.assertEquals(model.period(), dto.period());
    Assertions.assertEquals(model.shift(), dto.shift());
    Assertions.assertEquals(model.method(), dto.method());
    Assertions.assertEquals(model.applyTo(), dto.applyTo());
  }

  @Override
  protected void thenExtraParameters(IMASlowCreateDto createDto, IMASlow model) {
    Assertions.assertEquals(createDto.period(), model.period());
    Assertions.assertEquals(createDto.shift(), model.shift());
    Assertions.assertEquals(createDto.method(), model.method());
    Assertions.assertEquals(createDto.applyTo(), model.applyTo());
  }
}