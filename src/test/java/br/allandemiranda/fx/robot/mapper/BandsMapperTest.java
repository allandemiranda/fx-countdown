package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.BandsDto;
import br.allandemiranda.fx.robot.dto.create.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.ChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.Bands;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BandsMapperTest extends ChartObjectMapperTest<Bands, BandsDto, BandsCreateDto> {

  @InjectMocks
  @Getter
  private BandsMapper mapper;

  @Mock
  @Getter
  private Bands model;

  @Mock
  @Getter
  private BandsCreateDto createDto;

  @Mock
  private BigDecimal baseLine;

  @Mock
  private BigDecimal upperBand;

  @Mock
  private BigDecimal lowerBand;

  @Override
  protected void whenExtraParameters(Bands model) {
    Mockito.when(model.baseLine()).thenReturn(this.baseLine);
    Mockito.when(model.upperBand()).thenReturn(this.upperBand);
    Mockito.when(model.lowerBand()).thenReturn(this.lowerBand);
  }

  @Override
  protected void whenExtraParameters(BandsCreateDto createDto) {
    Mockito.when(createDto.baseLine()).thenReturn(this.baseLine);
    Mockito.when(createDto.upperBand()).thenReturn(this.upperBand);
    Mockito.when(createDto.lowerBand()).thenReturn(this.lowerBand);
  }

  @Override
  protected void thenExtraParameters(Bands model, BandsDto dto) {
    Assertions.assertEquals(model.baseLine(), dto.baseLine());
    Assertions.assertEquals(model.upperBand(), dto.upperBand());
    Assertions.assertEquals(model.lowerBand(), dto.lowerBand());
  }

  @Override
  protected void thenExtraParameters(BandsCreateDto createDto, Bands model) {
    Assertions.assertEquals(createDto.baseLine(), model.baseLine());
    Assertions.assertEquals(createDto.upperBand(), model.upperBand());
    Assertions.assertEquals(createDto.lowerBand(), model.lowerBand());
  }

}