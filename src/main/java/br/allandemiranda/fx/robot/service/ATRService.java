package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ATRDto;
import br.allandemiranda.fx.robot.dto.create.ATRCreateDto;
import br.allandemiranda.fx.robot.mapper.ATRMapper;
import br.allandemiranda.fx.robot.model.ATR;
import br.allandemiranda.fx.robot.repository.ATRRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
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
