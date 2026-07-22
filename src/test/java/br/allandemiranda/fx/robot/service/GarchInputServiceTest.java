package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.GarchInputDto;
import br.allandemiranda.fx.robot.dto.create.GarchInputCreateDto;
import br.allandemiranda.fx.robot.mapper.GarchInputMapper;
import br.allandemiranda.fx.robot.model.GarchInput;
import br.allandemiranda.fx.robot.repository.GarchInputRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GarchInputServiceTest extends InputObjectServiceTest<GarchInput, GarchInputDto, GarchInputCreateDto> {

  @Mock
  @Getter
  private GarchInput model;

  @Mock
  @Getter
  private GarchInputRepository repository;

  @Spy
  @Getter
  private GarchInputMapper mapper;

  @InjectMocks
  @Getter
  private GarchInputService service;

  @Mock
  @Getter
  private GarchInputCreateDto createDto;
}
