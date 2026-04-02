package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.create.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.base.BandsDto;
import br.allandemiranda.fx.robot.mapper.BandsMapper;
import br.allandemiranda.fx.robot.model.Bands;
import br.allandemiranda.fx.robot.repository.BandsRepository;
import br.allandemiranda.fx.robot.service.contract.ChartObjectService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter(AccessLevel.PRIVATE)
@AllArgsConstructor
public final class BandsService implements ChartObjectService<Bands, BandsDto, BandsCreateDto, BandsRepository> {

  private final BandsRepository repository;

  private final BandsMapper mapper;

}
