package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.BandsDto;
import br.allandemiranda.fx.robot.dto.impl.create.BandsCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.BandsMapper;
import br.allandemiranda.fx.robot.model.impl.Bands;
import br.allandemiranda.fx.robot.repository.impl.BandsRepository;
import br.allandemiranda.fx.robot.service.ChartObjectService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class BandsService implements ChartObjectService<Bands, BandsDto, BandsCreateDto> {

  private final BandsRepository repository;

  private final BandsMapper mapper;

}
