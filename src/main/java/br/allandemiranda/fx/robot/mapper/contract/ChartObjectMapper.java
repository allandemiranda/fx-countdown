package br.allandemiranda.fx.robot.mapper.contract;

import br.allandemiranda.fx.robot.dto.base.ChartDto;
import br.allandemiranda.fx.robot.dto.definition.ChartObjectDto;
import br.allandemiranda.fx.robot.dto.definition.CreateChartObjectDto;
import br.allandemiranda.fx.robot.model.definition.ChartObjectModel;
import java.util.UUID;

public interface ChartObjectMapper<M extends ChartObjectModel, D extends ChartObjectDto, C extends CreateChartObjectDto> {

  D toDto(ChartDto chartDto, M model);

  M toModel(UUID id, ChartDto chartDto, C createDto);

}
