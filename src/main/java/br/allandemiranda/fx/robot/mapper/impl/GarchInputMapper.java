package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.ChartDto;
import br.allandemiranda.fx.robot.dto.impl.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.InputObjectMapper;
import br.allandemiranda.fx.robot.model.impl.GarchInput;
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
