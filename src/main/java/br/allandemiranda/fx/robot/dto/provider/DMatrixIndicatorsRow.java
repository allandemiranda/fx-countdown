package br.allandemiranda.fx.robot.dto.provider;

import br.allandemiranda.fx.robot.annotation.model.DMatrixRowIndicatorsValidate;
import br.allandemiranda.fx.robot.model.indicator.ADX;
import br.allandemiranda.fx.robot.model.indicator.ATR;
import br.allandemiranda.fx.robot.model.indicator.Bands;
import br.allandemiranda.fx.robot.model.indicator.MACD;
import br.allandemiranda.fx.robot.model.indicator.MaFast;
import br.allandemiranda.fx.robot.model.indicator.MaSlow;
import br.allandemiranda.fx.robot.model.indicator.RSI;
import br.allandemiranda.fx.robot.model.indicator.Stochastic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@DMatrixRowIndicatorsValidate
public interface DMatrixIndicatorsRow {

  @NotNull
  @NotEmpty
  List<? extends ADX> adxs();

  @NotNull
  @NotEmpty
  List<? extends ATR> atrs();

  @NotNull
  @NotEmpty
  List<? extends Bands> bandss();

  @NotNull
  @NotEmpty
  List<? extends MaFast> maFasts();

  @NotNull
  @NotEmpty
  List<? extends MaSlow> maSlows();

  @NotNull
  @NotEmpty
  List<? extends MACD> macds();

  @NotNull
  @NotEmpty
  List<? extends RSI> rsis();

  @NotNull
  @NotEmpty
  List<? extends Stochastic> stochastics();
}
