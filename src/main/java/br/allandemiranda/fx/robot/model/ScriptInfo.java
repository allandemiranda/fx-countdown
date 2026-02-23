package br.allandemiranda.fx.robot.model;

import br.allandemiranda.fx.robot.model.embeddable.IADX;
import br.allandemiranda.fx.robot.model.embeddable.IATR;
import br.allandemiranda.fx.robot.model.embeddable.IBands;
import br.allandemiranda.fx.robot.model.embeddable.IMA;
import br.allandemiranda.fx.robot.model.embeddable.IMACD;
import br.allandemiranda.fx.robot.model.embeddable.IRSI;
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
import org.hibernate.proxy.HibernateProxy;

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
  private UUID id;

  @OneToOne(mappedBy = "scriptInfo")
  private Chart chart;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  private LocalDateTime updateTime;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  private LocalDateTime startScope;

  @PastOrPresent
  @Column(nullable = false, updatable = false)
  private LocalDateTime endScope;

  // Trend

  @Embedded
  private IADX iAdx;

  @Embedded
  private IBands iBands;

  @Embedded
  private IMA iMaFast;

  @Embedded
  private IMA iMaSlow;

  // Oscillators

  @Embedded
  private IATR iAtr;

  @Embedded
  private IMACD iMacd;

  @Embedded
  private IRSI iRsi;

  @Embedded
  private IStochastic iStochastic;

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