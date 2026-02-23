package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.embeddable.LMDataBuildGarch;
import br.allandemiranda.fx.robot.model.embeddable.LMDataBuildStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "LM_DATA_BUILD")
public class LMDataBuild {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  @JdbcTypeCode(SqlTypes.UUID)
  private UUID id;

  @Exclude
  @ManyToOne(optional = false)
  @JoinColumn(name = "chart_id", nullable = false)
  private Chart chart;

  // Build result

  @Embedded
  private LMDataBuildStatus status;

  // GARCH Data to build

  @Exclude
  @OneToMany
  private Set<Candlestick> candlesticksGarch = new LinkedHashSet<>();

  @Embedded
  private LMDataBuildGarch garch;

  // LM Data to build

  @Exclude
  @OneToMany
  private Set<Candlestick> candlesticks = new LinkedHashSet<>();

  // Trend

  @OneToMany
  @Exclude
  private Set<ADX> timelineADX = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<Bands> timelineBands = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<MaFast> timelineMaFast = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<MaSlow> timelineMaSlow = new LinkedHashSet<>();

  // Oscillators

  @OneToMany
  @Exclude
  private Set<ATR> timelineATR = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<MACD> timelineMACD = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<RSI> timelineRSI = new LinkedHashSet<>();

  @OneToMany
  @Exclude
  private Set<Stochastic> timelineStochastics = new LinkedHashSet<>();

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
    LMDataBuild that = (LMDataBuild) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
