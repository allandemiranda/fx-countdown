package br.allandemiranda.fx.robot.validation;

import br.allandemiranda.fx.robot.model.Tick;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AskBidValidatorTest {

  @InjectMocks
  private AskBidValidator validator;

  @Mock
  private ConstraintValidatorContext context;

  @Mock
  private ConstraintViolationBuilder violationBuilder;

  @Mock
  private NodeBuilderCustomizableContext nodeBuilder;

  @Mock
  private Tick tick;

  @BeforeEach
  void setUp() {
    Mockito.lenient().when(context.buildConstraintViolationWithTemplate(Mockito.anyString())).thenReturn(violationBuilder);
    Mockito.lenient().when(violationBuilder.addPropertyNode(Mockito.anyString())).thenReturn(nodeBuilder);
  }

  @Test
  void shouldReturnTrueWhenAskIsGreaterThanBid() {
    //given
    BigDecimal ask = new BigDecimal("1.1005");
    BigDecimal bid = new BigDecimal("1.1000");

    //when
    Mockito.when(this.tick.ask()).thenReturn(ask);
    Mockito.when(this.tick.bid()).thenReturn(bid);
    boolean result = this.validator.isValid(this.tick, this.context);

    //then
    Assertions.assertTrue(result);
    Mockito.verifyNoInteractions(this.context);
  }

  @Test
  void shouldReturnTrueWhenAskIsEqualToBid() {
    //given
    BigDecimal ask = new BigDecimal("1.1000");
    BigDecimal bid = new BigDecimal("1.1000");

    //when
    Mockito.when(this.tick.ask()).thenReturn(ask);
    Mockito.when(this.tick.bid()).thenReturn(bid);
    boolean result = this.validator.isValid(this.tick, this.context);

    //then
    Assertions.assertTrue(result);
    Mockito.verifyNoInteractions(context);
  }

  @Test
  void shouldReturnFalseWhenAskIsLessThanBid() {
    //given
    BigDecimal ask = new BigDecimal("1.0990");
    BigDecimal bid = new BigDecimal("1.1000");

    //when
    Mockito.when(this.tick.ask()).thenReturn(ask);
    Mockito.when(this.tick.bid()).thenReturn(bid);
    boolean result = this.validator.isValid(this.tick, this.context);

    //then
    Assertions.assertFalse(result);
    Mockito.verify(context).disableDefaultConstraintViolation();
    Mockito.verify(context).buildConstraintViolationWithTemplate("ask must be greater than or equal to bid");
    Mockito.verify(violationBuilder).addPropertyNode("ask");
    Mockito.verify(nodeBuilder).addConstraintViolation();
  }
}