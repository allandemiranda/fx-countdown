package br.allandemiranda.fx.robot.dto.file;

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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record MLDataRowDto(@NotNull UUID chartId, @NotNull @PastOrPresent OffsetDateTime timestamp,

                           // DATA ROW
                           @NotNull MLLabel label, @NotNull @NotEmpty List<@Valid ADX> adxList, @NotNull @NotEmpty List<@Valid ATR> atrList, @NotNull @NotEmpty List<@Valid Bands> bandsList, @NotNull @NotEmpty List<@Valid Candlestick> candlestickList, @NotNull @NotEmpty List<@Valid GarchForecast> garchForecastList,
                           @NotNull @NotEmpty List<@Valid MACD> macdList, @NotNull @NotEmpty List<@Valid MaFast> maFastList, @NotNull @NotEmpty List<@Valid MaSlow> maSlowList, @NotNull @NotEmpty List<@Valid RSI> rsiList, @NotNull @NotEmpty List<@Valid Stochastic> stochasticList) implements Serializable {

}
