package br.allandemiranda.fx.robot.service.impl;

import br.allandemiranda.fx.robot.dto.impl.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.impl.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.impl.MLInputMapper;
import br.allandemiranda.fx.robot.model.impl.MLInput;
import br.allandemiranda.fx.robot.repository.impl.MLInputRepository;
import br.allandemiranda.fx.robot.service.InputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MLInputServiceTest extends InputObjectServiceTest<MLInput, MLInputDto, MLInputCreateDto> {

  @Mock
  @Getter
  private MLInput model;

  @Mock
  @Getter
  private MLInputRepository repository;

  @Spy
  @Getter
  private MLInputMapper mapper;

  @InjectMocks
  @Getter
  private MLInputService service;

  @Mock
  @Getter
  private MLInputCreateDto createDto;
}
