package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.dto.DMatrixRowDto;
import br.allandemiranda.fx.robot.dto.core.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticCreateDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public record DMatrixPredictRowDto(
    @NotNull @NotEmpty GarchForecastDto garchForecastDto,
    @NotNull @NotEmpty List<CandlestickCreateDto> candlestickDtos,
    @NotNull @NotEmpty List<ADXCreateDto> adxDtos,
    @NotNull @NotEmpty List<ATRCreateDto> atrDtos,
    @NotNull @NotEmpty List<BandsCreateDto> bandsDtos,
    @NotNull @NotEmpty List<MACDCreateDto> macdDtos,
    @NotNull @NotEmpty List<MaFastCreateDto> maFastDtos,
    @NotNull @NotEmpty List<MaSlowCreateDto> maSlowDtos,
    @NotNull @NotEmpty List<RSICreateDto> rsiDtos,
    @NotNull @NotEmpty List<StochasticCreateDto> stochasticDtos
) implements Serializable,DMatrixRowDto {

}
