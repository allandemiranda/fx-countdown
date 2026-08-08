package br.allandemiranda.fx.robot.dto.operation;

import br.allandemiranda.fx.robot.dto.core.CandlestickCreateDto;
import br.allandemiranda.fx.robot.dto.core.TickCreateDto;
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

public record RequestSignalDto(
    @NotNull @NotEmpty List<TickCreateDto> ticks,
    @NotNull @NotEmpty List<CandlestickCreateDto> candlesticks,
    @NotNull @NotEmpty List<ADXCreateDto> adxs,
    @NotNull @NotEmpty List<ATRCreateDto> atrs,
    @NotNull @NotEmpty List<BandsCreateDto> bandss,
    @NotNull @NotEmpty List<MACDCreateDto> macds,
    @NotNull @NotEmpty List<MaFastCreateDto> maFasts,
    @NotNull @NotEmpty List<MaSlowCreateDto> maSlows,
    @NotNull @NotEmpty List<RSICreateDto> rsis,
    @NotNull @NotEmpty List<StochasticCreateDto> stochastics
) implements Serializable {

}
