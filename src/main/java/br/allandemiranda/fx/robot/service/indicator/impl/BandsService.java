package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.BandsDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.BandsMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.BandsEntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.BandsRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class BandsService implements IndicatorService<BandsEntry, BandsDto, BandsCreateDto> {

  private final BandsMapper mapper;
  private final BandsRepository repository;

}
