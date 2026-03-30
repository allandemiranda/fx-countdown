package br.allandemiranda.fx.robot.config;

import br.allandemiranda.fx.robot.converter.JsonListReadingConverter;
import br.allandemiranda.fx.robot.converter.JsonListWritingConverter;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

@Configuration
public class R2dbcConfig {

  @Bean
  public R2dbcCustomConversions R2dbcConvertersConfig(ObjectMapper objectMapper) {
    return R2dbcCustomConversions.of(
        PostgresDialect.INSTANCE,
        List.of(
            new JsonListWritingConverter(objectMapper),
            new JsonListReadingConverter<>(objectMapper, ADX.class),
            new JsonListReadingConverter<>(objectMapper, ATR.class),
            new JsonListReadingConverter<>(objectMapper, Bands.class),
            new JsonListReadingConverter<>(objectMapper, Candlestick.class),
            new JsonListReadingConverter<>(objectMapper, GarchForecast.class),
            new JsonListReadingConverter<>(objectMapper, MACD.class),
            new JsonListReadingConverter<>(objectMapper, MaFast.class),
            new JsonListReadingConverter<>(objectMapper, MaSlow.class),
            new JsonListReadingConverter<>(objectMapper, RSI.class),
            new JsonListReadingConverter<>(objectMapper, Stochastic.class)
        )
    );
  }
}