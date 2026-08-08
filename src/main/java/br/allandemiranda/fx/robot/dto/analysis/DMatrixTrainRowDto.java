package br.allandemiranda.fx.robot.dto.analysis;

import br.allandemiranda.fx.robot.dto.DMatrixRowDto;
import br.allandemiranda.fx.robot.dto.Timeseries;
import br.allandemiranda.fx.robot.dto.core.CandlestickDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ADXDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.ATRDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MACDDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaFastDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.MaSlowDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.RSIDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.StochasticDto;
import br.allandemiranda.fx.robot.enums.XGBoostLabel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public record DMatrixTrainRowDto(
    @NotNull @PastOrPresent OffsetDateTime timestamp,
    @NotNull XGBoostLabel label,
    @NotNull @NotEmpty GarchForecastDto garchForecastDto,
    @NotNull @NotEmpty List<CandlestickDto> candlestickDtos,
    @NotNull @NotEmpty List<ADXDto> adxDtos,
    @NotNull @NotEmpty List<ATRDto> atrDtos,
    @NotNull @NotEmpty List<BandsDto> bandsDtos,
    @NotNull @NotEmpty List<MACDDto> macdDtos,
    @NotNull @NotEmpty List<MaFastDto> maFastDtos,
    @NotNull @NotEmpty List<MaSlowDto> maSlowDtos,
    @NotNull @NotEmpty List<RSIDto> rsiDtos,
    @NotNull @NotEmpty List<StochasticDto> stochasticDtos
) implements Serializable, Timeseries, DMatrixRowDto {

}
