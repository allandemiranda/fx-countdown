package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.create.GarchForecastCreateDto;
import br.allandemiranda.fx.robot.mapper.contract.AbstractChartObjectMapperTest;
import br.allandemiranda.fx.robot.model.GarchForecast;
import java.math.BigDecimal;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GarchForecastMapperTest extends AbstractChartObjectMapperTest<GarchForecast, GarchForecastDto, GarchForecastCreateDto> {

  @InjectMocks
  @Getter
  private GarchForecastMapper mapper;

  @Mock
  @Getter
  private GarchForecast model;

  @Mock
  @Getter
  private GarchForecastCreateDto createDto;

  @Mock
  private BigDecimal omega;

  @Mock
  private BigDecimal alpha;

  @Mock
  private BigDecimal beta;

  @Mock
  private BigDecimal sigmaAgg;

  @Override
  protected void whenExtraParameters(GarchForecast model) {
    Mockito.when(model.omega()).thenReturn(this.omega);
    Mockito.when(model.alpha()).thenReturn(this.alpha);
    Mockito.when(model.beta()).thenReturn(this.beta);
    Mockito.when(model.sigmaAgg()).thenReturn(this.sigmaAgg);
  }

  @Override
  protected void whenExtraParameters(GarchForecastCreateDto createDto) {
    Mockito.when(createDto.omega()).thenReturn(this.omega);
    Mockito.when(createDto.alpha()).thenReturn(this.alpha);
    Mockito.when(createDto.beta()).thenReturn(this.beta);
    Mockito.when(createDto.sigmaAgg()).thenReturn(this.sigmaAgg);
  }

  @Override
  protected void thenExtraParameters(GarchForecast model, GarchForecastDto dto) {
    Assertions.assertEquals(model.omega(), dto.omega());
    Assertions.assertEquals(model.alpha(), dto.alpha());
    Assertions.assertEquals(model.beta(), dto.beta());
    Assertions.assertEquals(model.sigmaAgg(), dto.sigmaAgg());
  }

  @Override
  protected void thenExtraParameters(GarchForecastCreateDto createDto, GarchForecast model) {
    Assertions.assertEquals(createDto.omega(), model.omega());
    Assertions.assertEquals(createDto.alpha(), model.alpha());
    Assertions.assertEquals(createDto.beta(), model.beta());
    Assertions.assertEquals(createDto.sigmaAgg(), model.sigmaAgg());
  }

}