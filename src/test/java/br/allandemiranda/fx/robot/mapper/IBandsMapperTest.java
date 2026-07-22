package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.enums.AppliedPrice;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.IBands;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IBandsMapperTest extends InputObjectMapperTest<IBands, IBandsDto, IBandsCreateDto> {

  private final short period = 1;
  private final short shift = 2;

  @InjectMocks
  @Getter
  private IBandsMapper mapper;

  @Mock
  @Getter
  private IBands model;

  @Mock
  @Getter
  private IBandsCreateDto createDto;

  @Mock
  private BigDecimal deviations;

  @Mock
  private AppliedPrice applyTo;

  @Override
  protected void whenExtraParameters(IBands model) {
    Mockito.when(model.period()).thenReturn(this.period);
    Mockito.when(model.shift()).thenReturn(this.shift);
    Mockito.when(model.deviations()).thenReturn(this.deviations);
    Mockito.when(model.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void whenExtraParameters(IBandsCreateDto createDto) {
    Mockito.when(createDto.period()).thenReturn(this.period);
    Mockito.when(createDto.shift()).thenReturn(this.shift);
    Mockito.when(createDto.deviations()).thenReturn(this.deviations);
    Mockito.when(createDto.applyTo()).thenReturn(this.applyTo);
  }

  @Override
  protected void thenExtraParameters(IBands model, IBandsDto dto) {
    Assertions.assertEquals(model.period(), dto.period());
    Assertions.assertEquals(model.shift(), dto.shift());
    Assertions.assertEquals(model.deviations(), dto.deviations());
    Assertions.assertEquals(model.applyTo(), dto.applyTo());
  }

  @Override
  protected void thenExtraParameters(IBandsCreateDto createDto, IBands model) {
    Assertions.assertEquals(createDto.period(), model.period());
    Assertions.assertEquals(createDto.shift(), model.shift());
    Assertions.assertEquals(createDto.deviations(), model.deviations());
    Assertions.assertEquals(createDto.applyTo(), model.applyTo());
  }
}