package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.ATRDto;
import br.allandemiranda.fx.robot.dto.impl.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.ATRMapper;
import br.allandemiranda.fx.robot.model.impl.ATR;
import br.allandemiranda.fx.robot.repository.impl.ATRRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class ATRService implements ChartObjectService<ATR, ATRDto, ATRCreateDto> {

  private final ATRRepository repository;

  private final ATRMapper mapper;

}
