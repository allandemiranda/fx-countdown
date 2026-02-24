package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.annotation.AskBidValidate;
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
import jakarta.validation.constraints.Positive;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AskBidValidate
@Entity
@Table(name = "Tick", indexes = {
    @Index(name = "idx_tick_chart_id", columnList = "chart_id")
}, uniqueConstraints = {
    @UniqueConstraint(name = "uc_tick_chart_id_timestamp", columnNames = {"chart_id", "timestamp"})
})
public class Tick {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "chart_id", nullable = false, updatable = false)
  private Chart chart;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  @TimeZoneStorage(TimeZoneStorageType.AUTO)
  private ZonedDateTime timestamp;

  @Positive
  @Column(nullable = false, updatable = false, name = "ask")
  private double ask;

  @Positive
  @Column(nullable = false, updatable = false, name = "bid")
  private double bid;

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
    Tick tick = (Tick) o;
    return getId() != null && Objects.equals(getId(), tick.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
