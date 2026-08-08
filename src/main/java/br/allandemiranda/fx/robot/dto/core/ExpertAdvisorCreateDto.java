package br.allandemiranda.fx.robot.dto.core;

import br.allandemiranda.fx.robot.dto.impl.input.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.XGBoostInputCreateDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;

public record ExpertAdvisorCreateDto(@NotNull @Pattern(regexp = "^[A-Za-z0-9_-]{1,20}$") String name, @NotNull String description, @NotNull @Valid GarchInputCreateDto garch, @NotNull @Valid IADXCreateDto iadx,
                                     @NotNull @Valid IATRCreateDto iatr, @NotNull @Valid IBandsCreateDto ibands, @NotNull @Valid IMACDCreateDto imacd, @NotNull @Valid IMAFastCreateDto imaFast,
                                     @NotNull @Valid IMASlowCreateDto imaSlow, @NotNull @Valid IRSICreateDto irsi, @NotNull @Valid IStochasticCreateDto iStochastic, @NotNull @Valid PriceRiskLevelInputCreateDto priceRiskLevel,
                                     @NotNull @Valid ScopeInputCreateDto scope, @NotNull @Valid XGBoostInputCreateDto xgBoost) implements Serializable {

}