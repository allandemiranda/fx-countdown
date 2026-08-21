package br.allandemiranda.fx.robot.dto.ea.create;

import br.allandemiranda.fx.robot.dto.input.create.impl.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IADXCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IATRCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IBandsCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMAFastCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IMASlowCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IRSICreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.PriceRiskLevelInputCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.ScopeInputCreateDto;
import br.allandemiranda.fx.robot.dto.input.create.impl.XgBoostInputCreateDto;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisor;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorInputs;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorParameters;
import java.io.Serializable;

public record ExpertAdvisorCreateDto(
    String eaName,
    String description,
    GarchInputCreateDto garch,
    IADXCreateDto iadx,
    IATRCreateDto iatr,
    IBandsCreateDto ibands,
    IMACDCreateDto imacd,
    IMAFastCreateDto imaFast,
    IMASlowCreateDto imaSlow,
    IRSICreateDto irsi,
    IStochasticCreateDto iStochastic,
    PriceRiskLevelInputCreateDto priceRiskLevel,
    ScopeInputCreateDto scope,
    XgBoostInputCreateDto xgBoost
) implements Serializable, ExpertAdvisor, ExpertAdvisorParameters, ExpertAdvisorInputs {

}
