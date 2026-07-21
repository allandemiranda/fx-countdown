package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractInputObjectMapperTest;
import br.allandemiranda.fx.robot.model.GarchInput;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchInputMapperTest extends AbstractInputObjectMapperTest<GarchInput, GarchInputDto, GarchInputCreateDto> {

  private final int horizon = 1;
  private final int priceSize = 2;

  @InjectMocks
  @Getter
  private GarchInputMapper mapper;

  @Mock
  @Getter
  private GarchInput model;

  @Mock
  @Getter
  private GarchInputCreateDto createDto;
  @Mock
  private BigDecimal kTP;

  @Mock
  private BigDecimal kSL;

  @Override
  protected void whenExtraParameters(GarchInput model) {
    Mockito.when(model.horizon()).thenReturn(this.horizon);
    Mockito.when(model.priceSize()).thenReturn(this.priceSize);
    Mockito.when(model.kTP()).thenReturn(this.kTP);
    Mockito.when(model.kSL()).thenReturn(this.kSL);
  }

  @Override
  protected void whenExtraParameters(GarchInputCreateDto createDto) {
    Mockito.when(createDto.horizon()).thenReturn(this.horizon);
    Mockito.when(createDto.priceSize()).thenReturn(this.priceSize);
    Mockito.when(createDto.kTP()).thenReturn(this.kTP);
    Mockito.when(createDto.kSL()).thenReturn(this.kSL);
  }

  @Override
  protected void thenExtraParameters(GarchInput model, GarchInputDto dto) {
    Assertions.assertEquals(model.horizon(), dto.horizon());
    Assertions.assertEquals(model.priceSize(), dto.priceSize());
    Assertions.assertEquals(model.kTP(), dto.kTP());
    Assertions.assertEquals(model.kSL(), dto.kSL());
  }

  @Override
  protected void thenExtraParameters(GarchInputCreateDto createDto, GarchInput model) {
    Assertions.assertEquals(createDto.horizon(), model.horizon());
    Assertions.assertEquals(createDto.priceSize(), model.priceSize());
    Assertions.assertEquals(createDto.kTP(), model.kTP());
    Assertions.assertEquals(createDto.kSL(), model.kSL());
  }
}