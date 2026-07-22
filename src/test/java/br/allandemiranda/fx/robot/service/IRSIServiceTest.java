package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IRSIDto;
import br.allandemiranda.fx.robot.dto.create.IRSICreateDto;
import br.allandemiranda.fx.robot.mapper.IRSIMapper;
import br.allandemiranda.fx.robot.model.IRSI;
import br.allandemiranda.fx.robot.repository.IRSIRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IRSIServiceTest extends InputObjectServiceTest<IRSI, IRSIDto, IRSICreateDto> {

  @Mock
  @Getter
  private IRSI model;

  @Mock
  @Getter
  private IRSIRepository repository;

  @Spy
  @Getter
  private IRSIMapper mapper;

  @InjectMocks
  @Getter
  private IRSIService service;

  @Mock
  @Getter
  private IRSICreateDto createDto;
}
