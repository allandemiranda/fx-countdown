package br.allandemiranda.fx.robot.service.impl.indicator;

import br.allandemiranda.fx.robot.dto.impl.indicator.BandsCreateDto;
import br.allandemiranda.fx.robot.dto.impl.indicator.BandsDto;
import br.allandemiranda.fx.robot.mapper.impl.indicator.BandsMapper;
import br.allandemiranda.fx.robot.model.impl.indicator.Bands;
import br.allandemiranda.fx.robot.repository.impl.indicator.BandsRepository;
import br.allandemiranda.fx.robot.service.IndicatorService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class BandsService implements IndicatorService<Bands, BandsDto, BandsCreateDto> {

  private final BandsRepository repository;

  private final BandsMapper mapper;

}
