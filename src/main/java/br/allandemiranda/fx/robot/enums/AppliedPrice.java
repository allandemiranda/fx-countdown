package br.allandemiranda.fx.robot.enums;

import br.allandemiranda.fx.robot.dto.provider.Candlestick;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * Enumeration of applied price types for indicator calculations, corresponding to MQL5 {@code ENUM_APPLIED_PRICE}.
 */
@NullMarked
@Getter
@RequiredArgsConstructor
public enum AppliedPrice {

  /**
   * Bar Close price.
   */
  PRICE_CLOSE("Close"),

  /**
   * Bar Open price.
   */
  PRICE_OPEN("Open"),

  /**
   * Bar High price.
   */
  PRICE_HIGH("High"),

  /**
   * Bar Low price.
   */
  PRICE_LOW("Low"),

  /**
   * Median price: (High + Low) / 2.
   */
  PRICE_MEDIAN("Median Price (HL/2)"),

  /**
   * Typical price: (High + Low + Close) / 3.
   */
  PRICE_TYPICAL("Typical Price (HLC/3)"),

  /**
   * Weighted close price: (High + Low + Close * 2) / 4.
   */
  PRICE_WEIGHTED("Weighted Close (HLCC/4)");

  private final String description;

  /**
   * Calculates the specific applied price value from a candlesticks bar.
   *
   * @param <C>            the candlesticks type
   * @param candlestickDto the candlesticks instance
   * @return calculated price as a {@link BigDecimal}
   */
  @Contract(pure = true)
  public <C extends Candlestick> BigDecimal getPrice(C candlestickDto) {
    int scale = candlestickDto.close().scale();

    return switch (this) {
      case PRICE_CLOSE -> candlestickDto.close();
      case PRICE_OPEN -> candlestickDto.open();
      case PRICE_HIGH -> candlestickDto.high();
      case PRICE_LOW -> candlestickDto.low();
      case PRICE_MEDIAN -> PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto)).divide(BigDecimal.TWO, scale, RoundingMode.HALF_UP);
      case PRICE_TYPICAL -> PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto)).add(PRICE_CLOSE.getPrice(candlestickDto)).divide(BigDecimal.valueOf(3L), scale, RoundingMode.HALF_UP);
      case PRICE_WEIGHTED ->
          PRICE_HIGH.getPrice(candlestickDto).add(PRICE_LOW.getPrice(candlestickDto)).add(PRICE_CLOSE.getPrice(candlestickDto).multiply(BigDecimal.TWO)).divide(BigDecimal.valueOf(4L), scale, RoundingMode.HALF_UP);
    };
  }
}