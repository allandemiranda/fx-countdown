package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.MACDDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.MACDMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.MACDEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.MACDRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class MACDService implements IndicatorService<MACDEntry, MACDDto, MACDCreateDto> {

  private final MACDMapper mapper;
  private final MACDRepository repository;

}
