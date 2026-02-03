package br.allandemiranda.fx.robot.mapper.impl;

import br.allandemiranda.fx.robot.dto.impl.base.DashboardDto;
import br.allandemiranda.fx.robot.dto.impl.create.DashboardCreateDto;
import br.allandemiranda.fx.robot.enums.DashboardStatus;
import br.allandemiranda.fx.robot.mapper.InputObjectMapperTest;
import br.allandemiranda.fx.robot.model.impl.Dashboard;
import java.time.OffsetDateTime;
import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DashboardMapperTest extends InputObjectMapperTest<Dashboard, DashboardDto, DashboardCreateDto> {

  @InjectMocks
  @Getter
  private DashboardMapper mapper;

  @Mock
  @Getter
  private Dashboard model;

  @Mock
  @Getter
  private DashboardCreateDto createDto;

  @Mock
  private DashboardStatus status;

  @Mock
  private OffsetDateTime updateTime;

  @Mock
  private OffsetDateTime startScope;

  @Mock
  private OffsetDateTime endScope;

  @Override
  protected void whenExtraParameters(Dashboard model) {
    Mockito.when(model.status()).thenReturn(this.status);
    Mockito.when(model.updateTime()).thenReturn(this.updateTime);
    Mockito.when(model.startScope()).thenReturn(this.startScope);
    Mockito.when(model.endScope()).thenReturn(this.endScope);
  }

  @Override
  protected void whenExtraParameters(DashboardCreateDto createDto) {
    Mockito.when(createDto.startScope()).thenReturn(this.startScope);
    Mockito.when(createDto.endScope()).thenReturn(this.endScope);
  }

  @Override
  protected void thenExtraParameters(Dashboard model, DashboardDto dto) {
    Assertions.assertEquals(model.status(), dto.status());
    Assertions.assertEquals(model.updateTime(), dto.updateTime());
    Assertions.assertEquals(model.startScope(), dto.startScope());
    Assertions.assertEquals(model.endScope(), dto.endScope());
  }

  @Override
  protected void thenExtraParameters(DashboardCreateDto createDto, Dashboard model) {
    Assertions.assertEquals(createDto.status(), model.status());
    Assertions.assertFalse(model.updateTime().isAfter(OffsetDateTime.now()));
    Assertions.assertEquals(createDto.startScope(), model.startScope());
    Assertions.assertEquals(createDto.endScope(), model.endScope());
  }
}