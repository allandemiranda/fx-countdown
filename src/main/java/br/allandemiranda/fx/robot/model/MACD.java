package br.allandemiranda.fx.robot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "MACD", indexes = {
    @Index(name = "idx_macd_symbol_period_timestamp_unq", columnList = "chart_id, timestamp", unique = true)
}, uniqueConstraints = {
    @UniqueConstraint(name = "uc_macd_symbol_period_timestamp", columnNames = {"chart_id", "timestamp"})
})
@Entity
public class MACD {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  @JdbcTypeCode(SqlTypes.UUID)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "chart_id", nullable = false)
  private Chart chart;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime timestamp;

  @Column(nullable = false, precision = 8, scale = 8, updatable = false, name = "main_line")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal mainLine;

  @Column(nullable = false, precision = 8, scale = 8, updatable = false, name = "signal_line")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal signalLine;

  @Override
  public final boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
    Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) {
      return false;
    }
    MACD macd = (MACD) o;
    return getId() != null && Objects.equals(getId(), macd.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
