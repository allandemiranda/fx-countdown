package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.Timeseries;
import java.math.BigDecimal;

public interface RSI extends Timeseries {

  BigDecimal rsi();
}
