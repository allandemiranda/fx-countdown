package br.allandemiranda.fx.robot.service.impl.input;

import br.allandemiranda.fx.robot.dto.impl.input.IMACDCreateDto;
import br.allandemiranda.fx.robot.dto.impl.input.IMACDDto;
import br.allandemiranda.fx.robot.mapper.impl.input.IMACDMapper;
import br.allandemiranda.fx.robot.model.impl.input.IMACD;
import br.allandemiranda.fx.robot.repository.impl.input.IMACDRepository;
import br.allandemiranda.fx.robot.service.InputService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
@AllArgsConstructor
public class IMACDService implements InputService<IMACD, IMACDDto, IMACDCreateDto> {

  private final IMACDRepository repository;
  private final IMACDMapper mapper;

}
