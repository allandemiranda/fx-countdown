package br.allandemiranda.fx.robot.mapper.impl.input;

import br.allandemiranda.fx.robot.dto.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.InputDashboardMapper;
import br.allandemiranda.fx.robot.model.impl.input.GarchInput;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public final class GarchInputMapper implements InputDashboardMapper<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Override
  public GarchInputDto toDto(DashboardDto dashboardDto, GarchInput garchInput) {
    return new GarchInputDto(garchInput.id(), dashboardDto, garchInput.horizon(), garchInput.priceSize(), garchInput.kTP(), garchInput.kSL());
  }

  @Override
  public GarchInput toModel(UUID id, DashboardDto dashboardDto, GarchInputCreateDto garchInputCreateDto) {
    return new GarchInput(id, dashboardDto.id(), garchInputCreateDto.horizon(), garchInputCreateDto.priceSize(), garchInputCreateDto.kTP(), garchInputCreateDto.kSL());
  }
}
