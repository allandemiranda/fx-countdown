package br.allandemiranda.fx.robot.mapper;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.mapper.contract.InputObjectMapper;
import br.allandemiranda.fx.robot.model.GarchInput;
import org.springframework.stereotype.Component;

@Component
public final class GarchInputMapper implements InputObjectMapper<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Override
  public GarchInputDto toDto(ChartDto chartDto, GarchInput garchInput) {
    return new GarchInputDto(chartDto, garchInput.horizon(), garchInput.priceSize(), garchInput.kTP(), garchInput.kSL());
  }

  @Override
  public GarchInput toModel(ChartDto chartDto, GarchInputCreateDto garchInputCreateDto) {
    return new GarchInput(chartDto.id(), garchInputCreateDto.horizon(), garchInputCreateDto.priceSize(), garchInputCreateDto.kTP(), garchInputCreateDto.kSL());
  }
}
