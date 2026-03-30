package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.MLLabel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ml_data_row")
public record MLDataRow(@Id @Column("id") @NotNull UUID id, @Column("chart_id") @NotNull UUID chartId, @Column("timestamp") @NotNull @PastOrPresent OffsetDateTime timestamp,

                        // DATA ROW
                        @Column("label") @NotNull MLLabel label, @Column("adx_list") @NotNull @NotEmpty List<ADX> adxList, @Column("atr_list") @NotNull @NotEmpty List<ATR> atrList, @Column("band_list") @NotNull @NotEmpty List<Bands> bandsList,
                        @Column("candlestick_list") @NotNull @NotEmpty List<Candlestick> candlestickList, @Column("garch_forecast_list") @NotNull @NotEmpty List<GarchForecast> garchForecastList, @Column("macd_list") @NotNull @NotEmpty List<MACD> macdList,
                        @Column("ma_fast_list") @NotNull @NotEmpty List<MaFast> maFastList, @Column("ma_slow_list") @NotNull @NotEmpty List<MaSlow> maSlowList, @Column("rsi_list") @NotNull @NotEmpty List<RSI> rsiList, @Column("stochastic_list") @NotNull @NotEmpty List<Stochastic> stochasticList) {

}
