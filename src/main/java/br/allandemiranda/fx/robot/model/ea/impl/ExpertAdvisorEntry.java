package br.allandemiranda.fx.robot.model.ea.impl;

import br.allandemiranda.fx.robot.enums.EAStatus;
import br.allandemiranda.fx.robot.enums.Timeframe;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisor;
import br.allandemiranda.fx.robot.model.ea.ExpertAdvisorStatus;
import br.allandemiranda.fx.robot.model.provider.Chart;
import java.io.Serializable;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("ea_expert_advisor")
public record ExpertAdvisorEntry(
    @Id @Column("ea_name") String eaName,
    @Column("symbol_name") String symbolName,
    @Column("timeframe") Timeframe timeframe,
    @Column("update_time") OffsetDateTime updateTime,
    @Column("status") EAStatus status,
    @Column("description") String description
) implements Serializable, Chart, ExpertAdvisorStatus, ExpertAdvisor {

}
