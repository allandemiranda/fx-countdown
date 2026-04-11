package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IMAFastDto;
import br.allandemiranda.fx.robot.dto.create.IMAFastCreateDto;
import br.allandemiranda.fx.robot.mapper.IMAFastMapper;
import br.allandemiranda.fx.robot.model.IMAFast;
import br.allandemiranda.fx.robot.repository.IMAFastRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IMAFastServiceTest extends AbstractInputObjectServiceTest<IMAFast, IMAFastDto, IMAFastCreateDto> {

  @Mock
  @Getter
  private IMAFast model;

  @Mock
  @Getter
  private IMAFastRepository repository;

  @Spy
  @Getter
  private IMAFastMapper mapper;

  @InjectMocks
  @Getter
  private IMAFastService service;

  @Mock
  @Getter
  private IMAFastCreateDto createDto;
}
