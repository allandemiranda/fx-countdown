package br.allandemiranda.fx.robot.model.ea;

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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ExpertAdvisorInputs {

  @NotNull
  @Valid
  GarchInputCreateDto garch();

  @NotNull
  @Valid
  IStochasticCreateDto iStochastic();

  @NotNull
  @Valid
  IADXCreateDto iadx();

  @NotNull
  @Valid
  IATRCreateDto iatr();

  @NotNull
  @Valid
  IBandsCreateDto ibands();

  @NotNull
  @Valid
  IMAFastCreateDto imaFast();

  @NotNull
  @Valid
  IMASlowCreateDto imaSlow();

  @NotNull
  @Valid
  IMACDCreateDto imacd();

  @NotNull
  @Valid
  IRSICreateDto irsi();

  @NotNull
  @Valid
  PriceRiskLevelInputCreateDto priceRiskLevel();

  @NotNull
  @Valid
  ScopeInputCreateDto scope();

  @NotNull
  @Valid
  XgBoostInputCreateDto xgBoost();
}
