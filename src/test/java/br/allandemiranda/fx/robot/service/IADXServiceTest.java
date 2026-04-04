package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IADXDto;
import br.allandemiranda.fx.robot.dto.create.IADXCreateDto;
import br.allandemiranda.fx.robot.mapper.IADXMapper;
import br.allandemiranda.fx.robot.model.IADX;
import br.allandemiranda.fx.robot.repository.IADXRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IADXServiceTest extends AbstractInputObjectServiceTest<IADX, IADXDto, IADXCreateDto> {

  @Mock
  @Getter
  private IADX model;

  @Mock
  @Getter
  private IADXRepository repository;

  @Spy
  @Getter
  private IADXMapper mapper;

  @InjectMocks
  @Getter
  private IADXService service;
}
