package br.allandemiranda.fx.robot.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
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
public class Symbol {

  @Id
  @Column(nullable = false, length = 6, updatable = false, unique = true, comment = "Symbol name")
  @Size(min = 6, max = 6)
  @Pattern(regexp = "^[A-Z]{6}$")
  @JdbcTypeCode(SqlTypes.VARCHAR)
  private String name;

  @Positive
  @Column(nullable = false, precision = 7, scale = 6, updatable = false, comment = "Symbol point value")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal point;

  @Column(nullable = false, precision = 6, scale = 3, updatable = false, name = "swap_long", comment = "Long swap value")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal swapLong;

  @Column(nullable = false, precision = 6, scale = 3, updatable = false, name = "swap_short", comment = "Short swap value")
  @JdbcTypeCode(SqlTypes.DECIMAL)
  private BigDecimal swapShort;

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
