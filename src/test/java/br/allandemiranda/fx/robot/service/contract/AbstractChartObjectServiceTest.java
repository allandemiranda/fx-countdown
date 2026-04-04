package br.allandemiranda.fx.robot.service.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import br.allandemiranda.fx.robot.repository.contract.ChartObjectRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public abstract class AbstractChartObjectServiceTest<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  @Mock
  private UUID chartId;

  private Validator validator;

  protected abstract ChartObjectRepository<M> getRepository();

  protected abstract ChartObjectService<M, D, C> getService();

  protected abstract M createValidAbstractMockModel();

  protected abstract M createIdNullAbstractMockModel();

  protected UUID getChartId() {
    return this.chartId;
  }

  @BeforeEach
  void setupValidator() {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      this.validator = factory.getValidator();
    }
  }

  @Test
  void model_ShouldBeValid_WhenFieldsAreCorrect() {
    //given
    M model = this.createValidAbstractMockModel();
    //when
    Set<ConstraintViolation<M>> violations = this.validator.validate(model);
    //then
    Assertions.assertTrue(violations.isEmpty(), "The model has validation violations: " + violations);
  }

  @Test
    // para continuar para as outras violações
  void model_ShouldNotBeValid_WhenIdIsNull() {
    //given
    M model = this.createIdNullAbstractMockModel();
    //when
    Set<ConstraintViolation<M>> violations = this.validator.validate(model);
    Optional<ConstraintViolation<M>> violation = violations.stream().filter(v -> v.getPropertyPath().toString().equals("id")).findFirst();
    //then
    Assertions.assertTrue(violation.isPresent(), "Should have a violation on 'id' parameter");
    Assertions.assertEquals("{jakarta.validation.constraints.NotNull.message}", violation.get().getMessageTemplate(), "Should have a not null violation on 'id' parameter");
  }

  @Test
  void getByChartDtoAndTimestamp_ShouldReturnDto_WhenModelExists() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    M model = this.createValidAbstractMockModel();
    //when
    Mockito.when(chartDto.id()).thenReturn(this.getChartId());
    Mockito.when(this.getRepository().findByChartIdAndTimestamp(chartDto.id(), model.timestamp())).thenReturn(Mono.just(model));

    //then
    StepVerifier.create(this.getService().get(chartDto, model.timestamp())).assertNext(dto -> {
      Assertions.assertNotNull(dto.id());
      Assertions.assertEquals(model.id(), dto.id());

      Assertions.assertNotNull(dto.chartDto());
      Assertions.assertEquals(this.getChartId(), dto.chartDto().id());

      Assertions.assertNotNull(dto.timestamp());
      Assertions.assertEquals(model.timestamp(), dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getByChartDtoAndTimestamp_ShouldReturnVoid_WhenModelNotExists() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    OffsetDateTime timestamp = Mockito.mock(OffsetDateTime.class);
    UUID uuid = Mockito.mock(UUID.class);
    //when
    Mockito.when(chartDto.id()).thenReturn(uuid);
    Mockito.when(this.getRepository().findByChartIdAndTimestamp(uuid, timestamp)).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().get(chartDto, timestamp)).expectSubscription().expectComplete().verify();
  }

  @Test
  void getAll_ShouldReturnFluxOfDtos() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    M model = this.createValidAbstractMockModel();
    //when
    Mockito.when(chartDto.id()).thenReturn(this.getChartId());
    Mockito.when(this.getRepository().findAllByChartId(chartDto.id())).thenReturn(Flux.just(model));
    //then
    StepVerifier.create(this.getService().get(chartDto).collectList()).assertNext(dtos -> {
      Assertions.assertEquals(1, dtos.size());
      D dto = dtos.getFirst();

      Assertions.assertNotNull(dto.id());
      Assertions.assertEquals(model.id(), dto.id());

      Assertions.assertNotNull(dto.chartDto());
      Assertions.assertEquals(this.getChartId(), dto.chartDto().id());

      Assertions.assertNotNull(dto.timestamp());
      Assertions.assertEquals(model.timestamp(), dto.timestamp());
    }).verifyComplete();
  }

  @Test
  void getAll_ShouldReturnEmptyFlux_IfNotExistsChartDto() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    //when
    Mockito.when(chartDto.id()).thenReturn(this.getChartId());
    Mockito.when(this.getRepository().findAllByChartId(chartDto.id())).thenReturn(Flux.just());
    //then
    StepVerifier.create(this.getService().get(chartDto).collectList()).assertNext(dtos -> {
      Assertions.assertEquals(0, dtos.size());
    }).verifyComplete();
  }

  @Test
  void delete_ShouldReturnEmptyMono() {
    //given
    ChartDto chartDto = Mockito.mock(ChartDto.class);
    //when
    Mockito.when(chartDto.id()).thenReturn(this.getChartId());
    Mockito.when(this.getRepository().deleteAllByChartId(chartDto.id())).thenReturn(Mono.empty());
    //then
    StepVerifier.create(this.getService().delete(chartDto)).verifyComplete();
  }
}