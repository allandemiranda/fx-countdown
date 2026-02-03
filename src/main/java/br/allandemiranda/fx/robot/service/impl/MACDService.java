package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MACDDto;
import br.allandemiranda.fx.robot.dto.impl.create.MACDCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MACDMapper;
import br.allandemiranda.fx.robot.model.impl.MACD;
import br.allandemiranda.fx.robot.repository.impl.MACDRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
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
