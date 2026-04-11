package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IMASlowDto;
import br.allandemiranda.fx.robot.dto.create.IMASlowCreateDto;
import br.allandemiranda.fx.robot.mapper.IMASlowMapper;
import br.allandemiranda.fx.robot.model.IMASlow;
import br.allandemiranda.fx.robot.repository.IMASlowRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IMASlowServiceTest extends AbstractInputObjectServiceTest<IMASlow, IMASlowDto, IMASlowCreateDto> {

  @Mock
  @Getter
  private IMASlow model;

  @Mock
  @Getter
  private IMASlowRepository repository;

  @Spy
  @Getter
  private IMASlowMapper mapper;

  @InjectMocks
  @Getter
  private IMASlowService service;

  @Mock
  @Getter
  private IMASlowCreateDto createDto;
}
