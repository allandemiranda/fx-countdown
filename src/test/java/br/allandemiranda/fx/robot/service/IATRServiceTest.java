package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.IATRDto;
import br.allandemiranda.fx.robot.dto.create.IATRCreateDto;
import br.allandemiranda.fx.robot.mapper.IATRMapper;
import br.allandemiranda.fx.robot.model.IATR;
import br.allandemiranda.fx.robot.repository.IATRRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractInputObjectServiceTest;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IATRServiceTest extends AbstractInputObjectServiceTest<IATR, IATRDto, IATRCreateDto> {

  @Mock
  @Getter
  private IATR model;

  @Mock
  @Getter
  private IATRRepository repository;

  @Spy
  @Getter
  private IATRMapper mapper;

  @InjectMocks
  @Getter
  private IATRService service;

  @Mock
  @Getter
  private IATRCreateDto createDto;
}
