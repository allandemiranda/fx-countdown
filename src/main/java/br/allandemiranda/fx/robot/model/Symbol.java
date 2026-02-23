package br.allandemiranda.fx.robot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
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
public class Symbol {

  @Id
  @Column(nullable = false, length = 6, updatable = false, unique = true, comment = "Symbol name")
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  private String name;

  @Positive
  @Column(nullable = false, updatable = false, comment = "Symbol point value")
  private double point;

  @Column(nullable = false, updatable = false, name = "swap_long", comment = "Long swap value")
  private double swapLong;

  @Column(nullable = false, updatable = false, name = "swap_short", comment = "Short swap value")
  private double swapShort;

  @OneToMany(mappedBy = "symbol", cascade = CascadeType.REMOVE, orphanRemoval = true)
  @Exclude
  private Set<Chart> charts = new LinkedHashSet<>();

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
    Symbol symbol = (Symbol) o;
    return getName() != null && Objects.equals(getName(), symbol.getName());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
  }
}
