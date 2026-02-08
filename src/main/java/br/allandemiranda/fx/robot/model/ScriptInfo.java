package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.embeddable.IADX;
import br.allandemiranda.fx.robot.model.embeddable.IATR;
import br.allandemiranda.fx.robot.model.embeddable.IBands;
import br.allandemiranda.fx.robot.model.embeddable.IMA;
import br.allandemiranda.fx.robot.model.embeddable.iMACD;
import br.allandemiranda.fx.robot.model.embeddable.iRSI;
import br.allandemiranda.fx.robot.model.embeddable.IStochastic;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
  private IADX iAdx;

  @Embedded
  private IATR iAtr;

  @Embedded
  private IBands iBands;

  @Embedded
  private iMACD iMacd;

  @Embedded
  private iRSI iRsi;

  @Embedded
  private IStochastic iStochastic;

  @Embedded
  private IMA iMaSlow;

  @Embedded
  private IMA iMaFast;

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