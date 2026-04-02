package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.RSIDto;
import br.allandemiranda.fx.robot.dto.create.RSICreateDto;
import br.allandemiranda.fx.robot.mapper.RSIMapper;
import br.allandemiranda.fx.robot.model.RSI;
import br.allandemiranda.fx.robot.repository.RSIRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public final class RSIService implements ChartObjectService<RSI, RSIDto, RSICreateDto, RSIRepository> {

  private final RSIRepository repository;

  private final RSIMapper mapper;

}
