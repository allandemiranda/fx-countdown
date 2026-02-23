package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.enums.Timeframe;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
import org.hibernate.proxy.HibernateProxy;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "CHART", indexes = {
    @Index(name = "idx_chart_symbol_name_unq", columnList = "symbol_name, period", unique = true)
})
public class Chart {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "symbol_name", nullable = false, updatable = false)
  private Symbol symbol;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10, updatable = false, comment = "Chart Timeframe")
  private Timeframe period;

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<Candlestick> candlesticks = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<Tick> ticks = new LinkedHashSet<>();

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
  @JoinColumn(name = "script_info_id", nullable = false, unique = true)
  private ScriptInfo scriptInfo;

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<LMDataBuild> lmDataBuilds = new LinkedHashSet<>();

  // Trend

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<ADX> timelineADX = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<Bands> timelineBands = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<MaFast> timelineMaFast = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<MaSlow> timelineMaSlow = new LinkedHashSet<>();

  // Oscillators

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<ATR> timelineATR = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<MACD> timelineMACD = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<RSI> timelineRSI = new LinkedHashSet<>();

  @OneToMany(mappedBy = "chart", cascade = CascadeType.REMOVE, orphanRemoval = true)
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
    Chart chart = (Chart) o;
    return getId() != null && Objects.equals(getId(), chart.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
