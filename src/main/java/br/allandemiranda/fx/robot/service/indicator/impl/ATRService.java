package br.allandemiranda.fx.robot.service.indicator.impl;

import br.allandemiranda.fx.robot.dto.indicator.ATRDto;
import br.allandemiranda.fx.robot.dto.indicator.create.impl.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.indicator.impl.ATRMapper;
import br.allandemiranda.fx.robot.model.indicator.impl.ATREntry;
import br.allandemiranda.fx.robot.repository.indicator.impl.ATRRepository;
import br.allandemiranda.fx.robot.service.indicator.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter
@Service
public class ATRService implements IndicatorService<ATREntry, ATRDto, ATRCreateDto> {

  private final ATRMapper mapper;
  private final ATRRepository repository;

}
