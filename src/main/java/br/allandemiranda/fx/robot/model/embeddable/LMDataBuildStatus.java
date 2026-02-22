package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.DealReason;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.type.SqlTypes;

@Data
@Embeddable
public class LMDataBuildStatus {

  @Column(nullable = false, comment = "Timestamp open position")
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime openTime;

  @Column(comment = "Result of buy operation in points")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal buyPoints;

  @Column(comment = "Timestamp close buy position")
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime closeBuyTime;

  @Enumerated(EnumType.STRING)
  @Column()
  private DealReason buyDealReason;

  @Column(comment = "Result of sell operation in points")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal sellPoints;

  @Column(comment = "Timestamp close sell position")
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime closeSellTime;

  @Enumerated(EnumType.STRING)
  @Column()
  private DealReason sellDealReason;
}
