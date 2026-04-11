package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.MaSlowDto;
import br.allandemiranda.fx.robot.dto.create.MaSlowCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.MaSlow;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaSlowMapperTest extends AbstractChartObjectMapperTest<MaSlow, MaSlowDto, MaSlowCreateDto> {

  @InjectMocks
  @Getter
  private MaSlowMapper mapper;

  @Mock
  @Getter
  private MaSlow model;

  @Mock
  @Getter
  private MaSlowCreateDto createDto;

  @Mock
  private BigDecimal ma;

  @Override
  protected void whenExtraParameters(MaSlow model) {
    Mockito.when(model.ma()).thenReturn(ma);
  }

  @Override
  protected void whenExtraParameters(MaSlowCreateDto createDto) {
    Mockito.when(createDto.ma()).thenReturn(ma);
  }

  @Override
  protected void thenExtraParameters(MaSlow model, MaSlowDto dto) {
    Assertions.assertEquals(model.ma(), dto.ma());
  }

  @Override
  protected void thenExtraParameters(MaSlowCreateDto createDto, MaSlow model) {
    Assertions.assertEquals(createDto.ma(), model.ma());
  }

}