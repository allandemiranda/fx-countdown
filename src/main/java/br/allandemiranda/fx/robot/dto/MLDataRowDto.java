package br.allandemiranda.fx.robot.dto;

import br.allandemiranda.fx.robot.enums.MLLabel;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.model.ATR;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.model.Candlestick;
import br.allandemiranda.fx.robot.model.GarchForecast;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.model.MaFast;
import br.allandemiranda.fx.robot.model.MaSlow;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.model.Stochastic;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MLDataRowDto(@NotNull UUID chartId, @NotNull @PastOrPresent OffsetDateTime timestamp,

                           // DATA ROW
                           @NotNull MLLabel label, @NotNull @NotEmpty List<ADX> adxList, @NotNull @NotEmpty List<ATR> atrList, @NotNull @NotEmpty List<Bands> bandsList, @NotNull @NotEmpty List<Candlestick> candlestickList, @NotNull @NotEmpty List<GarchForecast> garchForecastList,
                           @NotNull @NotEmpty List<MACD> macdList, @NotNull @NotEmpty List<MaFast> maFastList, @NotNull @NotEmpty List<MaSlow> maSlowList, @NotNull @NotEmpty List<RSI> rsiList, @NotNull @NotEmpty List<Stochastic> stochasticList) implements Serializable {

}
