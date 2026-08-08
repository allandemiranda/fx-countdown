package br.allandemiranda.fx.robot.dto.impl.indicator;

import br.allandemiranda.fx.robot.dto.Timeseries;
import java.math.BigDecimal;

public interface ADX extends Timeseries {

  BigDecimal mainLine();

  BigDecimal plusDiLine();

  BigDecimal minusDiLine();
}
