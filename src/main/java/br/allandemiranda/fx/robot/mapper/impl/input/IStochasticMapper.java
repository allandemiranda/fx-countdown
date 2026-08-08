package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IStochasticDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.IStochastic;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class IStochasticMapper implements InputDashboardMapper<IStochastic, IStochasticDto, IStochasticCreateDto> {

  public IStochasticDto toDto(DashboardDto dashboardDto, IStochastic iStochastic) {
    return new IStochasticDto(iStochastic.id(), dashboardDto, iStochastic.kPeriod(), iStochastic.dPeriod(), iStochastic.slowing(), iStochastic.method(), iStochastic.priceField());
  }

  public IStochastic toModel(UUID id, DashboardDto dashboardDto, IStochasticCreateDto iStochasticCreateDto) {
    return new IStochastic(id, dashboardDto.id(), iStochasticCreateDto.kPeriod(), iStochasticCreateDto.dPeriod(), iStochasticCreateDto.slowing(), iStochasticCreateDto.method(), iStochasticCreateDto.priceField());
  }
}
