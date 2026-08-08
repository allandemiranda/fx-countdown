package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.dto.analysis.GarchForecastDto;
import br.allandemiranda.fx.robot.dto.core.Candlestick;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADX;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATR;
import br.allandemiranda.fx.robot.dto.impl.indicator.Bands;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACD;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFast;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlow;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSI;
import br.allandemiranda.fx.robot.dto.impl.indicator.Stochastic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public interface DMatrixRowDto {

  @NotNull
  GarchForecastDto garchForecastDto();

  @NotNull
  @NotEmpty
  List<? extends Candlestick> candlestickDtos();

  @NotNull
  @NotEmpty
  List<? extends ADX> adxDtos();

  @NotNull
  @NotEmpty
  List<? extends ATR> atrDtos();

  @NotNull
  @NotEmpty
  List<? extends Bands> bandsDtos();

  @NotNull
  @NotEmpty
  List<? extends MACD> macdDtos();

  @NotNull
  @NotEmpty
  List<? extends MaFast> maFastDtos();

  @NotNull
  @NotEmpty
  List<? extends MaSlow> maSlowDtos();

  @NotNull
  @NotEmpty
  List<? extends RSI> rsiDtos();

  @NotNull
  @NotEmpty
  List<? extends Stochastic> stochasticDtos();
}
