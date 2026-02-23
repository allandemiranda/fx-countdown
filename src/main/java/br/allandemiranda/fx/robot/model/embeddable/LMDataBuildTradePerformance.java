package br.allandemiranda.fx.robot.model.embeddable;

import br.allandemiranda.fx.robot.enums.DealReason;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.ZonedDateTime;
import lombok.Data;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

@Data
@Embeddable
public class LMDataBuildTradePerformance {

  @Column(nullable = false, comment = "Timestamp open position")
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime openTime;

  @Column(comment = "Result of buy operation in points")
  private double buyPoints;

  @Column(comment = "Timestamp close buy position")
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime closeBuyTime;

  @Enumerated(EnumType.STRING)
  @Column()
  private DealReason buyDealReason;

  @Column(comment = "Result of sell operation in points")
  private double sellPoints;

  @Column(comment = "Timestamp close sell position")
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime closeSellTime;

  @Enumerated(EnumType.STRING)
  @Column()
  private DealReason sellDealReason;
}
