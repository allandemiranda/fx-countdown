package br.allandemiranda.fx.robot.model.indicator.provider;

import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorId;
import br.allandemiranda.fx.robot.model.provider.Chart;
import br.allandemiranda.fx.robot.model.provider.DefaultId;
import br.allandemiranda.fx.robot.model.provider.Timeseries;

public interface Indicator extends Timeseries, DefaultId, ExpertAdvisorId, Chart {

}
