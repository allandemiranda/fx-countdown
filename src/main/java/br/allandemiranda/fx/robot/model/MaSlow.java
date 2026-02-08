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
@Entity
@Table(name = "MA_SLOW", indexes = {
    @Index(name = "idx_maSlow_symbol_period_timestamp", columnList = "chart_id, timestamp", unique = true)
}, uniqueConstraints = {
    @UniqueConstraint(name = "uc_maSlow_symbol_period_timestamp", columnNames = {"chart_id", "timestamp"})
})
public class MaSlow {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  @JdbcTypeCode(SqlTypes.UUID)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "chart_id", nullable = false, updatable = false)
  private Chart chart;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime timestamp;

  @Column(nullable = false, precision = 10, scale = 6, updatable = false, comment = "Returns the handle of a specified technical indicator")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal ma;

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
    MaSlow maSlow = (MaSlow) o;
    return getId() != null && Objects.equals(getId(), maSlow.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
