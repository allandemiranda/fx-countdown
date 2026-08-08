package br.allandemiranda.fx.robot.enums;

import br.allandemiranda.fx.robot.dto.core.Candlestick;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;

@Getter
@RequiredArgsConstructor
@Description("ENUM_APPLIED_PRICE")
public enum AppliedPrice {
  PRICE_CLOSE("Close"),
  PRICE_OPEN("Open"),
  PRICE_HIGH("High"),
  PRICE_LOW("Low"),
  PRICE_MEDIAN("Median Price (HL/2)"),
  PRICE_TYPICAL("Typical Price (HLC/2)"),
  PRICE_WEIGHTED("Weighted Close (HLCC/4)");

  @NonNull
  private final String description;

  @NonNull
  public <C extends Candlestick> BigDecimal getPrice(@NonNull C candlestickDto) {
    return switch (this) {
      case PRICE_CLOSE -> candlestickDto.close();
      case PRICE_OPEN -> candlestickDto.open();
      case PRICE_HIGH -> candlestickDto.high();
      case PRICE_LOW -> candlestickDto.low();
      case PRICE_MEDIAN -> (PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto))).divide(BigDecimal.TWO, RoundingMode.HALF_UP);
      case PRICE_TYPICAL -> (PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto)).add(PRICE_CLOSE.getPrice(candlestickDto))).divide(BigDecimal.TWO, RoundingMode.HALF_UP);
      case PRICE_WEIGHTED -> (PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto)).add(PRICE_CLOSE.getPrice(candlestickDto).multiply(BigDecimal.TWO))).divide(BigDecimal.valueOf(4L), RoundingMode.HALF_UP);
      case null -> throw new IllegalStateException("Unexpected value null for candlestickDto");
    };
  }
}
