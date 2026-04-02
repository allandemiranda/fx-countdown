package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.GarchTradingDto;
import br.allandemiranda.fx.robot.dto.create.GarchTradingCreateDto;
import br.allandemiranda.fx.robot.mapper.GarchTradingMapper;
import br.allandemiranda.fx.robot.model.GarchTrading;
import br.allandemiranda.fx.robot.repository.GarchTradingRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public final class GarchTradingService implements ChartObjectService<GarchTrading, GarchTradingDto, GarchTradingCreateDto, GarchTradingRepository> {

  private final GarchTradingRepository repository;

  private final GarchTradingMapper mapper;

}
