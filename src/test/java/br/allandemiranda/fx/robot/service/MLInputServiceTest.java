package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.MLInputDto;
import br.allandemiranda.fx.robot.dto.create.MLInputCreateDto;
import br.allandemiranda.fx.robot.mapper.MLInputMapper;
import br.allandemiranda.fx.robot.model.MLInput;
import br.allandemiranda.fx.robot.repository.MLInputRepository;
import br.allandemiranda.fx.robot.service.contract.InputObjectServiceTest;
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
