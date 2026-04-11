package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IBandsDto;
import br.allandemiranda.fx.robot.dto.create.IBandsCreateDto;
import br.allandemiranda.fx.robot.mapper.IBandsMapper;
import br.allandemiranda.fx.robot.model.IBands;
import br.allandemiranda.fx.robot.repository.IBandsRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IBandsServiceTest extends AbstractInputObjectServiceTest<IBands, IBandsDto, IBandsCreateDto> {

  @Mock
  @Getter
  private IBands model;

  @Mock
  @Getter
  private IBandsRepository repository;

  @Spy
  @Getter
  private IBandsMapper mapper;

  @InjectMocks
  @Getter
  private IBandsService service;
}
