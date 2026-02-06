package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.embeddable.iADX;
import br.allandemiranda.fx.robot.model.embeddable.iATR;
import br.allandemiranda.fx.robot.model.embeddable.iBands;
import br.allandemiranda.fx.robot.model.embeddable.iMA;
import br.allandemiranda.fx.robot.model.embeddable.iMACD;
import br.allandemiranda.fx.robot.model.embeddable.iRSI;
import br.allandemiranda.fx.robot.model.embeddable.iStochastic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ScriptInfo")
public class ScriptInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(nullable = false, updatable = false, unique = true)
  @JdbcTypeCode(SqlTypes.UUID)
  private UUID id;

  @OneToOne(mappedBy = "scriptInfo")
  private Chart chart;

  @PastOrPresent
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @Column(nullable = false, updatable = false)
  private LocalDateTime updateTime;

  @PastOrPresent
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @Column(nullable = false, updatable = false)
  private LocalDateTime startScope;

  @PastOrPresent
  @JdbcTypeCode(SqlTypes.TIMESTAMP_WITH_TIMEZONE)
  @Column(nullable = false, updatable = false)
  private LocalDateTime endScope;

  @Embedded
  private iADX iAdx;

  @Embedded
  private iATR iAtr;

  @Embedded
  private iBands iBands;

  @Embedded
  private iMACD iMacd;

  @Embedded
  private iRSI iRsi;

  @Embedded
  private iStochastic iStochastic;

  @Embedded
  private iMA iMaSlow;

  @Embedded
  private iMA iMaFast;

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
    ScriptInfo that = (ScriptInfo) o;
    return getId() != null && Objects.equals(getId(), that.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}