package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MACDDto;
import br.allandemiranda.fx.robot.dto.create.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.MACDMapper;
import br.allandemiranda.fx.robot.model.MACD;
import br.allandemiranda.fx.robot.repository.MACDRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class MACDService implements ChartObjectService<MACD, MACDDto, MACDCreateDto> {

  private final MACDRepository repository;

  private final MACDMapper mapper;

}
