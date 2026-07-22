package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.MaFastDto;
import br.allandemiranda.fx.robot.dto.create.MaFastCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.MaFast;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaFastMapperTest extends ChartObjectMapperTest<MaFast, MaFastDto, MaFastCreateDto> {

  @InjectMocks
  @Getter
  private MaFastMapper mapper;

  @Mock
  @Getter
  private MaFast model;

  @Mock
  @Getter
  private MaFastCreateDto createDto;

  @Mock
  private BigDecimal ma;


  @Override
  protected void whenExtraParameters(MaFast model) {
    Mockito.when(model.ma()).thenReturn(this.ma);
  }

  @Override
  protected void whenExtraParameters(MaFastCreateDto createDto) {
    Mockito.when(createDto.ma()).thenReturn(this.ma);
  }

  @Override
  protected void thenExtraParameters(MaFast model, MaFastDto dto) {
    Assertions.assertEquals(model.ma(), dto.ma());
  }

  @Override
  protected void thenExtraParameters(MaFastCreateDto createDto, MaFast model) {
    Assertions.assertEquals(createDto.ma(), model.ma());
  }

}