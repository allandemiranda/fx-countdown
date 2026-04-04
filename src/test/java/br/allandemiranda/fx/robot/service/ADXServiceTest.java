package br.allandemiranda.fx.robot.service;

import br.allandemiranda.fx.robot.dto.base.ADXDto;
import br.allandemiranda.fx.robot.dto.create.ADXCreateDto;
import br.allandemiranda.fx.robot.mapper.ADXMapper;
import br.allandemiranda.fx.robot.model.ADX;
import br.allandemiranda.fx.robot.repository.ADXRepository;
import br.allandemiranda.fx.robot.service.contract.AbstractChartObjectServiceTest;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ADXServiceTest extends AbstractChartObjectServiceTest<ADX, ADXDto, ADXCreateDto> {

  @Mock
  @Getter
  private ADXRepository repository;

  @Spy
  @Getter
  private ADXMapper mapper;

  @InjectMocks
  @Getter
  private ADXService service;

  @Override
  protected ADX createValidAbstractMockModel() {
    return new ADX(UUID.randomUUID(), this.getChartId(), OffsetDateTime.now(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
  }

  @Override
  protected ADX createIdNullAbstractMockModel() {
    return new ADX(null, this.getChartId(), OffsetDateTime.now(), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
  }

}